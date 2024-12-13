package com.netflix.clone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.tv.PopularTvEntity
import com.netflix.clone.data.local.entity.tv.TopRatedTvEntity

@Dao
interface TvDao {
    @Upsert
    suspend fun popularInsertAll(tv: List<PopularTvEntity>)

    @Upsert
    suspend fun topRatedInsertAll(tv: List<TopRatedTvEntity>)

    @Query("SELECT * FROM popular_tv ORDER BY id")
    fun popularPagingSource(): PagingSource<Int, PopularTvEntity>

    @Query("SELECT * FROM top_rated_tv ORDER BY id")
    fun topRatedPagingSource(): PagingSource<Int, TopRatedTvEntity>

    @Query("DELETE FROM popular_tv")
    suspend fun popularClearAll()

    @Query("DELETE FROM top_rated_tv")
    suspend fun topRatedClearAll()
}
