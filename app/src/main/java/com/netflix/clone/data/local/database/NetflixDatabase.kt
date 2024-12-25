package com.netflix.clone.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.netflix.clone.data.local.dao.MovieDetailsDao
import com.netflix.clone.data.local.dao.MoviesDao
import com.netflix.clone.data.local.dao.RemoteKeyDao
import com.netflix.clone.data.local.dao.SeriesDao
import com.netflix.clone.data.local.dao.SeriesDetailsDao
import com.netflix.clone.data.local.dao.TrendingDao
import com.netflix.clone.data.local.dao.UpdateTimeDao
import com.netflix.clone.data.local.dao.UserListDao
import com.netflix.clone.data.local.entity.RemoteKey
import com.netflix.clone.data.local.entity.UpdateTime
import com.netflix.clone.data.local.entity.list.UserListEntity
import com.netflix.clone.data.local.entity.movies.NowPlayingMoviesEntity
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.movies.UpcomingMoviesEntity
import com.netflix.clone.data.local.entity.movies.details.MovieDetailsEntity
import com.netflix.clone.data.local.entity.movies.details.MovieRecommendationCrossRef
import com.netflix.clone.data.local.entity.movies.details.MovieRecommendationEntity
import com.netflix.clone.data.local.entity.series.PopularSeriesEntity
import com.netflix.clone.data.local.entity.series.TopRatedSeriesEntity
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsEntity
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationEntity
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonEntity
import com.netflix.clone.data.local.entity.trending.TrendingEntity

@Database(
    entities = [
        PopularMoviesEntity::class, UpcomingMoviesEntity::class, NowPlayingMoviesEntity::class,
        PopularSeriesEntity::class, TopRatedSeriesEntity::class, TrendingEntity::class,
        UserListEntity::class, RemoteKey::class, UpdateTime::class, MovieDetailsEntity::class,
        MovieRecommendationCrossRef::class, MovieRecommendationEntity::class, SeriesDetailsEntity::class,
        SeriesSeasonEntity::class, SeriesSeasonCrossRef::class, SeriesRecommendationEntity::class,
        SeriesRecommendationCrossRef::class,
    ],
    version = 1,
)
abstract class NetflixDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

    abstract fun seriesDao(): SeriesDao

    abstract fun trendingDao(): TrendingDao

    abstract fun userListDao(): UserListDao

    abstract fun remoteKeyDao(): RemoteKeyDao

    abstract fun updateTimeDao(): UpdateTimeDao

    abstract fun movieDetailsDao(): MovieDetailsDao

    abstract fun seriesDetailsDao(): SeriesDetailsDao
}
