package com.netflix.clone.data.local.entity.movies.details

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(
    @PrimaryKey val movieId: Int,
    val backdropPath: String?,
    val cast: String?,
    val directors: String?,
    val genres: String?,
    val lastUpdated: Long?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val runtime: Int?,
    val title: String?,
)
