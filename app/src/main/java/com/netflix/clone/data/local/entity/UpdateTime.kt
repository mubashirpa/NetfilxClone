package com.netflix.clone.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "update_time")
data class UpdateTime(
    @PrimaryKey val type: String,
    val lastUpdated: Long,
)
