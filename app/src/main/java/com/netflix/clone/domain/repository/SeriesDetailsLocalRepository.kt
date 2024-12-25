package com.netflix.clone.domain.repository

import com.netflix.clone.data.local.entity.series.details.SeriesDetailsEntity
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsWithSeasons
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonEntity

interface SeriesDetailsLocalRepository {
    suspend fun getSeriesDetailsById(id: Int): SeriesDetailsWithSeasons?

    suspend fun clearCacheById(id: Int)

    suspend fun saveSeriesDetails(
        seriesDetails: SeriesDetailsEntity,
        seriesSeasons: List<SeriesSeasonEntity>,
        seriesSeasonCrossRef: List<SeriesSeasonCrossRef>,
    )
}
