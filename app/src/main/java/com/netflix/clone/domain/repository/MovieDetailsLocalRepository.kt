package com.netflix.clone.domain.repository

import com.netflix.clone.data.local.entity.movies.details.MovieDetailsEntity
import com.netflix.clone.data.local.entity.movies.details.MovieDetailsWithRecommendations
import com.netflix.clone.data.local.entity.movies.details.MovieRecommendationCrossRef
import com.netflix.clone.data.local.entity.movies.details.RecommendationEntity

interface MovieDetailsLocalRepository {
    suspend fun getCachedMovieDetailsById(id: Int): MovieDetailsWithRecommendations?

    suspend fun clearCacheById(id: Int)

    suspend fun saveMovieDetails(
        movieDetails: MovieDetailsEntity,
        recommendations: List<RecommendationEntity>,
        movieRecommendationCrossRefs: List<MovieRecommendationCrossRef>,
    )
}