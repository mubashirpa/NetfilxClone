package com.netflix.clone.domain.usecase

import android.util.Log
import com.netflix.clone.core.utils.CacheUtils
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.local.entity.movies.details.MovieRecommendationCrossRef
import com.netflix.clone.data.remote.mapper.toMovieDetails
import com.netflix.clone.data.remote.mapper.toMovieDetailsWithRecommendations
import com.netflix.clone.domain.model.movie.MovieDetails
import com.netflix.clone.domain.repository.MovieDetailsLocalRepository
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val movieDetailsLocalRepository: MovieDetailsLocalRepository,
) {
    operator fun invoke(
        movieId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): Flow<Resource<MovieDetails>> =
        flow {
            try {
                emit(Resource.Loading())

                val cachedData = movieDetailsLocalRepository.getCachedMovieDetailsById(movieId)
                val lastUpdated = cachedData?.movieDetails?.lastUpdated ?: 0

                Log.d("hello", cachedData.toString())

                if (cachedData != null && CacheUtils.isCacheValid(lastUpdated)) {
                    emit(Resource.Success(cachedData.toMovieDetails()))
                    return@flow
                }

                val remoteDetails =
                    movieRepository
                        .getMovieDetails(movieId, appendToResponse, language)
                        .toMovieDetails()
                val movieDetailsWithRecommendations =
                    remoteDetails.toMovieDetailsWithRecommendations(
                        System.currentTimeMillis(),
                    )

                movieDetailsLocalRepository.saveMovieDetails(
                    movieDetails = movieDetailsWithRecommendations.movieDetails,
                    recommendations = movieDetailsWithRecommendations.recommendations,
                    movieRecommendationCrossRefs =
                        movieDetailsWithRecommendations.recommendations.map {
                            MovieRecommendationCrossRef(
                                movieId = movieDetailsWithRecommendations.movieDetails.movieId,
                                recommendationId = it.recommendationId,
                            )
                        },
                )

                emit(Resource.Success(remoteDetails))
            } catch (_: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (_: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (_: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
