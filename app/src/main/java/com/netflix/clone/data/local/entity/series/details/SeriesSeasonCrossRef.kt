package com.netflix.clone.data.local.entity.series.details

import androidx.room.Entity

@Entity(primaryKeys = ["seriesId", "seasonId"])
data class SeriesSeasonCrossRef(
    val seriesId: Int,
    val seasonId: Int,
)
