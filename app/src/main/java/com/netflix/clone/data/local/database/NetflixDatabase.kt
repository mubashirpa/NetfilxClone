package com.netflix.clone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netflix.clone.data.local.dao.MoviesDao
import com.netflix.clone.data.local.dao.RemoteKeyDao
import com.netflix.clone.data.local.dao.SeriesDao
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity

@Database(
    entities = [PopularMoviesEntity::class, PopularSeriesEntity::class, TopRatedSeriesEntity::class, RemoteKey::class],
    version = 1,
)
abstract class NetflixDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun seriesDao(): SeriesDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}
