package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.trending.TrendingResult
import com.netflix.clone.domain.model.trending.TrendingResultModel

fun TrendingResult.toTrendingResultModel(): TrendingResultModel =
    TrendingResultModel(
        firstAirDate,
        id,
        mediaType,
        name,
        posterPath,
        releaseDate,
        title,
        voteAverage,
    )
