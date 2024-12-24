package com.netflix.clone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netflix.clone.data.local.dao.MoviesDao
import com.netflix.clone.data.local.dao.RemoteKeyDao
import com.netflix.clone.data.local.dao.SeriesDao
import com.netflix.clone.data.local.dao.TrendingDao
import com.netflix.clone.data.local.dao.UpdateTimeDao
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.UpdateTime
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.local.entity.trending.TrendingEntity

@Database(
    entities = [
        PopularMoviesEntity::class, PopularSeriesEntity::class, TopRatedSeriesEntity::class,
        TrendingEntity::class, RemoteKey::class, UpdateTime::class,
    ],
    version = 1,
)
abstract class NetflixDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun seriesDao(): SeriesDao

    abstract fun trendingDao(): TrendingDao

    abstract fun remoteKeyDao(): RemoteKeyDao

    abstract fun updateTimeDao(): UpdateTimeDao
}
