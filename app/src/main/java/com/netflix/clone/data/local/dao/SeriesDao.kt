package com.netflix.clone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity

@Dao
interface SeriesDao {
    @Upsert
    suspend fun popularInsertAll(tv: List<PopularSeriesEntity>)

    @Upsert
    suspend fun topRatedInsertAll(tv: List<TopRatedSeriesEntity>)

    @Query("SELECT * FROM popular_series ORDER BY id")
    fun popularPagingSource(): PagingSource<Int, PopularSeriesEntity>

    @Query("SELECT * FROM top_rated_series ORDER BY id")
    fun topRatedPagingSource(): PagingSource<Int, TopRatedSeriesEntity>

    @Query("DELETE FROM popular_series")
    suspend fun popularClearAll()

    @Query("DELETE FROM top_rated_series")
    suspend fun topRatedClearAll()
}
