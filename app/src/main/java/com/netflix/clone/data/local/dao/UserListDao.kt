package com.netflix.clone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.list.UserListEntity

@Dao
interface UserListDao {
    @Upsert
    suspend fun insertAll(trending: List<UserListEntity>)

    @Query("SELECT * FROM user_list ORDER BY id")
    fun pagingSource(): PagingSource<Int, UserListEntity>

    @Query("DELETE FROM user_list")
    suspend fun clearAll()
}
