package com.netflix.clone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netflix.clone.data.local.dao.MoviesDao
import com.netflix.clone.data.local.dao.RemoteKeysDao
import com.netflix.clone.data.local.entity.movie.MovieEntity
import com.netflix.clone.data.local.entity.movie.RemoteKeys

@Database(
    entities = [MovieEntity::class, RemoteKeys::class],
    version = 1,
)
abstract class NetflixDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}
