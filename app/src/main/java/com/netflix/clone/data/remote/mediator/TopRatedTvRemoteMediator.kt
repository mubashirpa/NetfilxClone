package com.netflix.clone.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.mapper.toTopRatedSeriesEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TopRatedTvRemoteMediator(
    private val type: String,
    private val api: MovieApi,
    private val database: NetflixDatabase,
    private val language: String,
    private val page: Int,
) : RemoteMediator<Int, TopRatedSeriesEntity>() {
    val seriesDao = database.seriesDao()
    val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedSeriesEntity>,
    ): MediatorResult {
        return try {
            val loadKey =
                when (loadType) {
                    LoadType.REFRESH -> page

                    LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)

                    LoadType.APPEND -> {
                        val remoteKey =
                            database.withTransaction {
                                remoteKeyDao.remoteKeyByType(type)
                            }

                        if (remoteKey.nextKey == null) {
                            return MediatorResult.Success(
                                endOfPaginationReached = true,
                            )
                        }

                        remoteKey.nextKey
                    }
                }

            val response =
                api.getTopRatedSeries(
                    language = language,
                    page = loadKey,
                )
            val tv = response.results?.map { it.toTopRatedSeriesEntity() }.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByType(type)
                    seriesDao.topRatedClearAll()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(
                        type = type,
                        nextKey = response.page?.plus(1),
                    ),
                )
                seriesDao.topRatedInsertAll(tv)
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
