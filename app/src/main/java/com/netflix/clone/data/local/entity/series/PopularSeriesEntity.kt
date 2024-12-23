package com.netflix.clone.data.local.entity.series

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_series")
data class PopularSeriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val series: SeriesEntity,
)
