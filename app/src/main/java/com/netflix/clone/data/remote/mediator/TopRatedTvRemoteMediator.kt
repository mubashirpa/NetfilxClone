package com.netflix.clone.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.tv.TopRatedTvEntity
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.mapper.toTopRatedTvEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class TopRatedTvRemoteMediator(
    private val type: String,
    private val api: MovieApi,
    private val database: NetflixDatabase,
    private val language: String,
    private val page: Int,
) : RemoteMediator<Int, TopRatedTvEntity>() {
    val tvDao = database.tvDao()
    val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TopRatedTvEntity>,
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
            val tv = response.results?.map { it.toTopRatedTvEntity() }.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByType(type)
                    tvDao.topRatedClearAll()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(
                        type = type,
                        nextKey = response.page?.plus(1),
                    ),
                )
                tvDao.topRatedInsertAll(tv)
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
