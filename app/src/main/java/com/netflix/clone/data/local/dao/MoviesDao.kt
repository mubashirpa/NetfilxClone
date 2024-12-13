package com.netflix.clone.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity

@Dao
interface MoviesDao {
    @Upsert
    suspend fun insertAll(movies: List<PopularMoviesEntity>)

    @Query("SELECT * FROM popular_movies ORDER BY id")
    fun pagingSource(): PagingSource<Int, PopularMoviesEntity>

    @Query("DELETE FROM popular_movies")
    suspend fun clearAll()
}
