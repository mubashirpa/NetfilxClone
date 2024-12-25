package com.netflix.clone.data.local.entity.series.details

import androidx.room.Entity

@Entity(primaryKeys = ["seriesId", "recommendationId"])
data class SeriesRecommendationCrossRef(
    val seriesId: Int,
    val recommendationId: Int,
)
