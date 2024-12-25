package com.netflix.clone.data.local.entity.series.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "series_seasons")
data class SeriesSeasonEntity(
    @PrimaryKey val seasonId: Int,
    val airDate: String?,
    val episodeCount: Int?,
    val name: String?,
    val overview: String?,
    val posterPath: String?,
    val seriesId: Int?,
)
