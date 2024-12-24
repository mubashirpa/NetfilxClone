package com.netflix.clone.data.local.entity.movies.details

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.netflix.clone.data.local.entity.movies.MovieEntity

@Entity(tableName = "recommendations")
data class RecommendationEntity(
    @PrimaryKey val recommendationId: Int,
    @Embedded val movie: MovieEntity,
)
