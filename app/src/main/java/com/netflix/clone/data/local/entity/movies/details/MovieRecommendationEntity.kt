package com.netflix.clone.data.local.entity.movies.details

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.netflix.clone.data.local.entity.movies.MovieEntity

@Entity(tableName = "movie_recommendations")
data class MovieRecommendationEntity(
    @PrimaryKey val recommendationId: Int,
    @Embedded val movie: MovieEntity,
)
