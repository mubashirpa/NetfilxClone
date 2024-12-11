package com.netflix.clone.domain.usecase

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toMovieDetails
import com.netflix.clone.domain.model.movie.details.MovieDetails
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetMovieDetailsUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        movieId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): Flow<Resource<MovieDetails>> =
        flow {
            try {
                emit(Resource.Loading())
                val details =
                    movieRepository
                        .getMovieDetails(movieId, appendToResponse, language)
                        .toMovieDetails()
                emit(Resource.Success(details))
            } catch (_: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (_: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (_: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
