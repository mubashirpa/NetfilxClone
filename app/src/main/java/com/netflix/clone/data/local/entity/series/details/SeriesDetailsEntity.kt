package com.netflix.clone.data.local.entity.series.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series_details")
data class SeriesDetailsEntity(
    @PrimaryKey val seriesId: Int,
    val backdropPath: String?,
    val casts: String?,
    val createdBy: String?,
    val firstAirDate: String?,
    val genres: String?,
    val lastUpdated: Long?,
    val name: String?,
    val numberOfSeasons: Int?,
    val overview: String?,
)
