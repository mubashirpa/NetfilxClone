package com.netflix.clone.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.remote.MovieApi
import com.netflix.clone.data.remote.dto.list.ListResult
import com.netflix.clone.data.remote.dto.movie.MovieListsDto
import com.netflix.clone.data.remote.dto.movie.MovieResult
import com.netflix.clone.data.remote.dto.movie.details.MovieDetailsDto
import com.netflix.clone.data.remote.dto.series.SeriesResult
import com.netflix.clone.data.remote.dto.series.details.SeriesDetailsDto
import com.netflix.clone.data.remote.dto.trending.TrendingDto
import com.netflix.clone.data.remote.dto.trending.person.TrendingPersonDto
import com.netflix.clone.data.remote.mediator.PopularMoviesRemoteMediator
import com.netflix.clone.data.remote.mediator.PopularTvRemoteMediator
import com.netflix.clone.data.remote.mediator.TopRatedTvRemoteMediator
import com.netflix.clone.data.remote.paging.AiringTodaySeriesPagingSource
import com.netflix.clone.data.remote.paging.ListPagingSource
import com.netflix.clone.data.remote.paging.NowPlayingMoviesPagingSource
import com.netflix.clone.data.remote.paging.OnTheAirSeriesPagingSource
import com.netflix.clone.data.remote.paging.TopRatedMoviesPagingSource
import com.netflix.clone.data.remote.paging.UpcomingMoviesPagingSource
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
    ): Flow<PagingData<ListResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                ListPagingSource(movieApi, listId, language, page)
            },
        ).flow

    override suspend fun getNowPlayingMovies(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<MovieResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                NowPlayingMoviesPagingSource(movieApi, language, page, region)
            },
        ).flow

    override suspend fun getPopularMovies(
        language: String,
        page: Int,
        region: String?,
    ): MovieListsDto = movieApi.getPopularMovies(language, page, region)

    override suspend fun getPopularMoviesPaging(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<PopularMoviesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                PopularMoviesRemoteMediator(
                    type = "popular_movie",
                    api = movieApi,
                    database = database,
                    language = language,
                    page = page,
                    region = region,
                ),
            pagingSourceFactory = {
                database.moviesDao().pagingSource()
            },
        ).flow

    override suspend fun getTopRatedMovies(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<MovieResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                TopRatedMoviesPagingSource(movieApi, language, page, region)
            },
        ).flow

    override suspend fun getUpcomingMovies(
        language: String,
        page: Int,
        region: String?,
    ): Flow<PagingData<MovieResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                UpcomingMoviesPagingSource(movieApi, language, page, region)
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

    override suspend fun getTrendingPerson(
        timeWindow: TimeWindow,
        language: String,
    ): TrendingPersonDto = movieApi.getTrendingPerson(timeWindow.name.lowercase(), language)

    override suspend fun getAiringTodaySeries(
        language: String,
        page: Int,
        timezone: String?,
    ): Flow<PagingData<SeriesResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                AiringTodaySeriesPagingSource(movieApi, language, page, timezone)
            },
        ).flow

    override suspend fun getOnTheAirSeries(
        language: String,
        page: Int,
        timezone: String?,
    ): Flow<PagingData<SeriesResult>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = {
                OnTheAirSeriesPagingSource(movieApi, language, page, timezone)
            },
        ).flow

    override suspend fun getPopularSeries(
        language: String,
        page: Int,
    ): Flow<PagingData<PopularSeriesEntity>> =
        Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE),
            remoteMediator =
                PopularTvRemoteMediator(
                    type = "popular_series",
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
                    type = "top_rated_series",
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
