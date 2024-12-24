package com.netflix.clone.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.UpdateTime

@Dao
interface UpdateTimeDao {
    @Upsert
    suspend fun insertOrReplace(updateTime: UpdateTime)

    @Query("SELECT * FROM update_time WHERE type = :type")
    suspend fun updateTimeByType(type: String): UpdateTime?

    @Query("DELETE FROM update_time WHERE type = :type")
    suspend fun deleteByType(type: String)
}
