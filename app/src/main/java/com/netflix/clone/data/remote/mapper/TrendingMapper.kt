package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.trending.TrendingResult
import com.netflix.clone.domain.model.trending.Trending

fun TrendingResult.toTrending(): Trending =
    Trending(
        backdropPath = backdropPath,
        id = id,
        mediaType = mediaType,
        name = name,
        posterPath = posterPath,
        title = title,
    )
