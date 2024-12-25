package com.netflix.clone.data.local.entity.series.details

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.netflix.clone.data.local.entity.series.SeriesEntity

@Entity(tableName = "series_recommendations")
data class SeriesRecommendationEntity(
    @PrimaryKey val recommendationId: Int,
    @Embedded val series: SeriesEntity,
)
