package com.netflix.clone.domain.repository

import androidx.paging.PagingData
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.remote.dto.list.ListResult
import com.netflix.clone.data.remote.dto.movie.MovieListsDto
import com.netflix.clone.data.remote.dto.movie.MovieResult
import com.netflix.clone.data.remote.dto.movie.details.MovieDetailsDto
import com.netflix.clone.data.remote.dto.series.SeriesResult
import com.netflix.clone.data.remote.dto.series.details.SeriesDetailsDto
import com.netflix.clone.data.remote.dto.trending.TrendingDto
import com.netflix.clone.data.remote.dto.trending.person.TrendingPersonDto
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    // List

    suspend fun getList(
        listId: Int,
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<ListResult>>

    // Movie Lists

    suspend fun getNowPlayingMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<MovieResult>>

    suspend fun getPopularMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): MovieListsDto

    suspend fun getPopularMoviesPaging(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<PopularMoviesEntity>>

    suspend fun getTopRatedMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<MovieResult>>

    suspend fun getUpcomingMovies(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<MovieResult>>

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

    suspend fun getTrendingPerson(
        timeWindow: TimeWindow = TimeWindow.DAY,
        language: String = "en-US",
    ): TrendingPersonDto

    // TV Series Lists

    suspend fun getAiringTodaySeries(
        language: String = "en-US",
        page: Int = 1,
        timezone: String? = null,
    ): Flow<PagingData<SeriesResult>>

    suspend fun getOnTheAirSeries(
        language: String = "en-US",
        page: Int = 1,
        timezone: String? = null,
    ): Flow<PagingData<SeriesResult>>

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
