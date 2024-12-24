package com.netflix.clone.data.local.entity.list

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_list")
data class UserListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val listId: Int? = null,
    val mediaType: String? = null,
    val posterPath: String? = null,
)
