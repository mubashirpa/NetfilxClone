package com.netflix.clone.data.local.entity.series

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_rated_series")
data class TopRatedSeriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val series: SeriesEntity,
)
