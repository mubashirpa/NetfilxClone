package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.SeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsEntity
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsWithSeasons
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationEntity
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonEntity
import com.netflix.clone.data.remote.dto.series.SeriesResult
import com.netflix.clone.data.remote.dto.series.details.Season
import com.netflix.clone.data.remote.dto.series.details.SeriesDetailsDto
import com.netflix.clone.domain.model.series.Series
import com.netflix.clone.domain.model.series.SeriesDetails
import com.netflix.clone.domain.model.series.SeriesSeason

fun SeriesDetailsDto.toSeriesDetails(): SeriesDetails =
    SeriesDetails(
        backdropPath = backdropPath,
        casts = credits?.cast?.joinToString(", ") { it.name.orEmpty() },
        createdBy = createdBy?.joinToString(", ") { it.name.orEmpty() },
        firstAirDate = firstAirDate,
        genres = genres?.joinToString(", ") { it.name.orEmpty() },
        id = id,
        name = name,
        numberOfSeasons = numberOfSeasons,
        overview = overview,
        recommendations = recommendations?.results?.map { it.toSeries() },
        seasons = seasons?.map { it.toSeriesSeason() },
    )

fun Season.toSeriesSeason(): SeriesSeason =
    SeriesSeason(
        airDate = airDate,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
    )

fun SeriesResult.toSeries(): Series =
    Series(
        firstAirDate = firstAirDate,
        id = id,
        name = name,
        posterPath = posterPath,
        voteAverage = voteAverage,
    )

fun SeriesResult.toPopularSeriesEntity(): PopularSeriesEntity =
    PopularSeriesEntity(
        series =
            SeriesEntity(
                firstAirDate = firstAirDate,
                name = name,
                posterPath = posterPath,
                seriesId = id,
                voteAverage = voteAverage,
            ),
    )

fun PopularSeriesEntity.toSeries(): Series =
    Series(
        firstAirDate = series.firstAirDate,
        id = series.seriesId,
        name = series.name,
        posterPath = series.posterPath,
        voteAverage = series.voteAverage,
    )

fun SeriesResult.toTopRatedSeriesEntity(): TopRatedSeriesEntity =
    TopRatedSeriesEntity(
        series =
            SeriesEntity(
                firstAirDate = firstAirDate,
                name = name,
                posterPath = posterPath,
                seriesId = id,
                voteAverage = voteAverage,
            ),
    )

fun TopRatedSeriesEntity.toSeries(): Series =
    Series(
        firstAirDate = series.firstAirDate,
        id = series.seriesId,
        name = series.name,
        posterPath = series.posterPath,
        voteAverage = series.voteAverage,
    )

fun SeriesDetailsWithSeasons.toSeriesDetails(): SeriesDetails =
    SeriesDetails(
        backdropPath = seriesDetails.backdropPath,
        casts = seriesDetails.casts,
        createdBy = seriesDetails.createdBy,
        firstAirDate = seriesDetails.firstAirDate,
        genres = seriesDetails.genres,
        id = seriesDetails.seriesId,
        name = seriesDetails.name,
        numberOfSeasons = seriesDetails.numberOfSeasons,
        overview = seriesDetails.overview,
        recommendations = recommendations.map { it.toSeries() },
        seasons = seasons.map { it.toSeriesSeason() },
    )

fun SeriesSeasonEntity.toSeriesSeason(): SeriesSeason =
    SeriesSeason(
        airDate = airDate,
        episodeCount = episodeCount,
        id = seasonId,
        name = name,
        overview = overview,
        posterPath = posterPath,
    )

fun SeriesDetails.toSeriesDetailsWithSeasons(lastUpdated: Long): SeriesDetailsWithSeasons =
    SeriesDetailsWithSeasons(
        seriesDetails =
            SeriesDetailsEntity(
                seriesId = id!!,
                backdropPath = backdropPath,
                casts = casts,
                createdBy = createdBy,
                firstAirDate = firstAirDate,
                genres = genres,
                lastUpdated = lastUpdated,
                name = name,
                numberOfSeasons = numberOfSeasons,
                overview = overview,
            ),
        recommendations = recommendations?.map { it.toSeriesRecommendationEntity() } ?: emptyList(),
        seasons = seasons?.map { it.toSeriesSeasonEntity(id) } ?: emptyList(),
    )

fun SeriesSeason.toSeriesSeasonEntity(seriesId: Int): SeriesSeasonEntity =
    SeriesSeasonEntity(
        seasonId = id!!,
        airDate = airDate,
        episodeCount = episodeCount,
        name = name,
        overview = overview,
        posterPath = posterPath,
        seriesId = seriesId,
    )

fun SeriesRecommendationEntity.toSeries(): Series =
    Series(
        firstAirDate = series.firstAirDate,
        id = series.seriesId,
        name = series.name,
        posterPath = series.posterPath,
        voteAverage = series.voteAverage,
    )

fun Series.toSeriesRecommendationEntity(): SeriesRecommendationEntity =
    SeriesRecommendationEntity(
        recommendationId = id!!,
        series =
            SeriesEntity(
                firstAirDate = firstAirDate,
                name = name,
                posterPath = posterPath,
                seriesId = id,
                voteAverage = voteAverage,
            ),
    )
