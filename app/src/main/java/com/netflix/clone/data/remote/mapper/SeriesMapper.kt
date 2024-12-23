package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.SeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
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
