package com.netflix.clone.data.local.entity.movies.details

import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "recommendationId"])
data class MovieRecommendationCrossRef(
    val movieId: Int,
    val recommendationId: Int,
)
