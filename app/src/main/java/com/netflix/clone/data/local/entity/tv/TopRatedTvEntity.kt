package com.netflix.clone.data.local.entity.tv

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_rated_tv")
data class TopRatedTvEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @Embedded val tv: TvEntity,
)