package com.netflix.clone.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.movie.MovieEntity
import com.netflix.clone.data.local.entity.movie.RemoteKey
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.mapper.toMovieEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PopularMoviesRemoteMediator(
    private val type: String,
    private val api: MovieApi,
    private val database: NetflixDatabase,
    private val language: String,
    private val page: Int,
    private val region: String?,
) : RemoteMediator<Int, MovieEntity>() {
    val moviesDao = database.moviesDao()
    val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>,
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
                api.getPopularMovies(
                    language = language,
                    page = loadKey,
                    region = region,
                )
            val movies = response.results?.map { it.toMovieEntity() }.orEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByType(type)
                    moviesDao.clearAll()
                }

                remoteKeyDao.insertOrReplace(
                    RemoteKey(
                        type = type,
                        nextKey = response.page?.plus(1),
                    ),
                )
                database.moviesDao().insertAll(movies)
            }

            MediatorResult.Success(endOfPaginationReached = response.page == response.totalPages)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}
