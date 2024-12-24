package com.netflix.clone.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.list.UserListEntity
import com.netflix.clone.data.local.entity.movies.NowPlayingMoviesEntity
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.movies.UpcomingMoviesEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.dto.movie.details.MovieDetailsDto
import com.netflix.clone.data.remote.dto.series.details.SeriesDetailsDto
import com.netflix.clone.data.remote.dto.trending.TrendingDto
import com.netflix.clone.data.remote.mediator.NowPlayingMoviesRemoteMediator
import com.netflix.clone.data.remote.mediator.PopularMoviesRemoteMediator
import com.netflix.clone.data.remote.mediator.PopularTvRemoteMediator
import com.netflix.clone.data.remote.mediator.TopRatedTvRemoteMediator
import com.netflix.clone.data.remote.mediator.UpcomingMoviesRemoteMediator
import com.netflix.clone.data.remote.mediator.UserListRemoteMediator
import com.netflix.clone.domain.repository.MovieRepository
import com.netflix.clone.domain.repository.TimeWindow
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl(
    private val movieApi: MovieApi,
    private val database: NetflixDatabase,
) : MovieRepository {
    override suspend fun getList(
        listId: Int,
        language: String,
        page: Int,
    ): Flow<PagingData<UserListEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                UserListRemoteMediator(
                    api = movieApi,
                    database = database,
                    listId = listId,
                    language = language,
                    page = page,
                ),
            pagingSourceFactory = {
                database.userListDao().pagingSource()
            },
        ).flow

    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<NowPlayingMoviesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                NowPlayingMoviesRemoteMediator(
                    api = movieApi,
                    database = database,
                    language = language,
                    page = page,
                    region = region,
                ),
            pagingSourceFactory = {
                database.moviesDao().nowPlayingPagingSource()
            },
        ).flow

    override suspend fun getPopularMovies(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<PopularMoviesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                PopularMoviesRemoteMediator(
                    api = movieApi,
                    database = database,
                    language = language,
                    page = page,
                    region = region,
                ),
            pagingSourceFactory = {
                database.moviesDao().popularPagingSource()
            },
        ).flow

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<UpcomingMoviesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                UpcomingMoviesRemoteMediator(
                    api = movieApi,
                    database = database,
                    language = language,
                    page = page,
                    region = region,
                ),
            pagingSourceFactory = {
                database.moviesDao().upcomingPagingSource()
            },
        ).flow

    override suspend fun getMovieDetails(
        movieId: Int,
        appendToResponse: String?,
        language: String,
    ): MovieDetailsDto = movieApi.getMovieDetails(movieId, appendToResponse, language)

    override suspend fun getTrending(
        timeWindow: TimeWindow,
        language: String,
    ): TrendingDto = movieApi.getTrending(timeWindow.name.lowercase(), language)

    override suspend fun getPopularSeries(
        language: String,
        page: Int,
    ): Flow<PagingData<PopularSeriesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                PopularTvRemoteMediator(
                    api = movieApi,
                    database = database,
                    language = language,
                    page = page,
                ),
            pagingSourceFactory = {
                database.seriesDao().popularPagingSource()
            },
        ).flow

    override suspend fun getTopRatedSeries(
        language: String,
        page: Int,
    ): Flow<PagingData<TopRatedSeriesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                TopRatedTvRemoteMediator(
                    api = movieApi,
                    database = database,
                    language = language,
                    page = page,
                ),
            pagingSourceFactory = {
                database.seriesDao().topRatedPagingSource()
            },
        ).flow

    override suspend fun getSeriesDetails(
        seriesId: Int,
        appendToResponse: String?,
        language: String,
    ): SeriesDetailsDto = movieApi.getSeriesDetails(seriesId, appendToResponse, language)

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}
