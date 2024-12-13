package com.netflix.clone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netflix.clone.data.local.dao.MoviesDao
import com.netflix.clone.data.local.dao.RemoteKeyDao
import com.netflix.clone.data.local.dao.TvDao
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.tv.PopularTvEntity
import com.netflix.clone.data.local.entity.tv.TopRatedTvEntity

@Database(
    entities = [PopularMoviesEntity::class, PopularTvEntity::class, TopRatedTvEntity::class, RemoteKey::class],
    version = 1,
)
abstract class NetflixDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun tvDao(): TvDao

    abstract fun remoteKeyDao(): RemoteKeyDao
}
