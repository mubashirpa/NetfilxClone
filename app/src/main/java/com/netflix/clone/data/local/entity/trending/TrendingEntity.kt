package com.netflix.clone.data.local.entity.trending

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending")
data class TrendingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val backdropPath: String? = null,
    val mediaType: String? = null,
    val name: String? = null,
    val posterPath: String? = null,
    val title: String? = null,
    val trendingId: Int? = null,
)
