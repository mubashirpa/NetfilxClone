package com.netflix.clone.domain.usecase.movie

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toMovieResultModel
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<Resource<List<MovieResultModel>>> =
        flow {
            try {
                emit(Resource.Loading())
                val results =
                    movieRepository
                        .getPopularMovies(
                            language,
                            page,
                            region,
                        ).results
                        ?.map { it.toMovieResultModel() }
                emit(Resource.Success(results))
            } catch (e: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (e: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (e: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
