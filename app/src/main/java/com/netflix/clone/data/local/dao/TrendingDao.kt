package com.netflix.clone.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.trending.TrendingEntity

@Dao
interface TrendingDao {
    @Upsert
    suspend fun insertAll(trending: List<TrendingEntity>)

    @Query("SELECT * FROM trending ORDER BY id")
    suspend fun getAll(): List<TrendingEntity>

    @Query("DELETE FROM trending")
    suspend fun clearAll()
}
