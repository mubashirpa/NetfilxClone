package com.netflix.clone.domain.repository

import androidx.paging.PagingData
import com.netflix.clone.data.local.entity.list.UserListEntity
import com.netflix.clone.data.local.entity.movies.NowPlayingMoviesEntity
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.movies.UpcomingMoviesEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.remote.dto.movie.details.MovieDetailsDto
import com.netflix.clone.data.remote.dto.series.details.SeriesDetailsDto
import com.netflix.clone.data.remote.dto.trending.TrendingDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // List

    suspend fun getList(
        listId: Int,
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<UserListEntity>>

    // Movie Lists

    suspend fun getNowPlayingMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<NowPlayingMoviesEntity>>

    suspend fun getPopularMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<PopularMoviesEntity>>

    suspend fun getUpcomingMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<UpcomingMoviesEntity>>

    // Movies

    suspend fun getMovieDetails(
        movieId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): MovieDetailsDto

    // Trending

    suspend fun getTrending(
        timeWindow: TimeWindow = TimeWindow.DAY,
        language: String = "en-US",
    ): TrendingDto

    // TV Series Lists

    suspend fun getPopularSeries(
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<PopularSeriesEntity>>

    suspend fun getTopRatedSeries(
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<TopRatedSeriesEntity>>

    // TV Series

    suspend fun getSeriesDetails(
        seriesId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): SeriesDetailsDto
}

enum class TimeWindow {
    DAY,
    WEEK,
}
