package com.netflix.clone.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.movie.MovieEntity
import com.netflix.clone.data.local.entity.movie.RemoteKeys
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.mapper.toMovieEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PopularMoviesRemoteMediator(
    private val api: MovieApi,
    private val database: NetflixDatabase,
    private val language: String,
    private val page: Int,
    private val region: String?,
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>,
    ): MediatorResult {
        return try {
            val loadKey =
                when (loadType) {
                    LoadType.REFRESH -> {
                        val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                        remoteKeys?.nextKey?.minus(1) ?: page
                    }

                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                        val prevKey = remoteKeys?.prevKey
                        if (prevKey == null) {
                            return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                        }
                        prevKey
                    }

                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)

                        val nextKey = remoteKeys?.nextKey
                        if (nextKey == null) {
                            return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                        }
                        nextKey
                    }
                }

            val response =
                api.getPopularMovies(
                    language = language,
                    page = page,
                    region = region,
                )
            val movies = response.results?.map { it.toMovieEntity() }.orEmpty()
            val endOfPaginationReached = movies.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeysDao().clearRemoteKeys()
                    database.moviesDao().clearAll()
                }

                val prevKey = if (loadKey == page) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys =
                    movies.map {
                        RemoteKeys(
                            movieId = it.id,
                            prevKey = prevKey,
                            nextKey = nextKey,
                        )
                    }

                database.remoteKeysDao().insertAll(keys)
                database.moviesDao().insertAll(movies)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, MovieEntity>): RemoteKeys? =
        state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()
            ?.let { movie ->
                database.remoteKeysDao().remoteKeysRepoId(movie.id)
            }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, MovieEntity>): RemoteKeys? =
        state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()
            ?.let { movie ->
                database.remoteKeysDao().remoteKeysRepoId(movie.id)
            }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, MovieEntity>): RemoteKeys? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { movieId ->
                database.remoteKeysDao().remoteKeysRepoId(movieId)
            }
        }
}
