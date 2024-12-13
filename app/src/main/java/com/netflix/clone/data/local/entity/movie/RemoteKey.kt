package com.netflix.clone.data.local.entity.movie

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKey(
    @PrimaryKey val type: String,
    val nextKey: Int?,
)
