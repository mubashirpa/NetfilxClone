package com.netflix.clone.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.mapper.toPopularSeriesEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PopularTvRemoteMediator(
    private val type: String,
    private val api: MovieApi,
    private val database: NetflixDatabase,
    private val language: String,
    private val page: Int,
) : RemoteMediator<Int, PopularSeriesEntity>() {
    val seriesDao = database.seriesDao()
    val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularSeriesEntity>,
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
                api.getPopularSeries(
                    language = language,
                    page = loadKey,
                )
            val tv = response.results?.map { it.toPopularSeriesEntity() }.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByType(type)
                    seriesDao.popularClearAll()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(
                        type = type,
                        nextKey = response.page?.plus(1),
                    ),
                )
                seriesDao.popularInsertAll(tv)
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
