package com.netflix.clone.data.repository

import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.movies.details.MovieDetailsEntity
import com.netflix.clone.data.local.entity.movies.details.MovieDetailsWithRecommendations
import com.netflix.clone.data.local.entity.movies.details.MovieRecommendationCrossRef
import com.netflix.clone.data.local.entity.movies.details.RecommendationEntity
import com.netflix.clone.domain.repository.MovieDetailsLocalRepository

class MovieDetailsLocalRepositoryImpl(
    database: NetflixDatabase,
) : MovieDetailsLocalRepository {
    private val movieDetailsDao = database.movieDetailsDao()

    override suspend fun getCachedMovieDetailsById(id: Int): MovieDetailsWithRecommendations? =
        movieDetailsDao.getMovieDetailsWithRecommendations(id)

    override suspend fun clearCacheById(id: Int) {
        movieDetailsDao.deleteMovieDetailsWithCrossRefs(id)
    }

    override suspend fun saveMovieDetails(
        movieDetails: MovieDetailsEntity,
        recommendations: List<RecommendationEntity>,
        movieRecommendationCrossRefs: List<MovieRecommendationCrossRef>,
    ) {
        movieDetailsDao.upsertMovieDetailsWithRecommendations(
            movieDetails = movieDetails,
            recommendations = recommendations,
            movieRecommendationCrossRefs = movieRecommendationCrossRefs,
        )
    }
}
