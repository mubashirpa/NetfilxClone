package com.netflix.clone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.movies.UpcomingMoviesEntity

@Dao
interface MoviesDao {
    @Upsert
    suspend fun popularInsertAll(movies: List<PopularMoviesEntity>)

    @Upsert
    suspend fun upcomingInsertAll(movies: List<UpcomingMoviesEntity>)

    @Query("SELECT * FROM popular_movies ORDER BY id")
    fun popularPagingSource(): PagingSource<Int, PopularMoviesEntity>

    @Query("SELECT * FROM upcoming_movies ORDER BY id")
    fun upcomingPagingSource(): PagingSource<Int, UpcomingMoviesEntity>

    @Query("DELETE FROM popular_movies")
    suspend fun popularClearAll()

    @Query("DELETE FROM upcoming_movies")
    suspend fun upcomingClearAll()
}
