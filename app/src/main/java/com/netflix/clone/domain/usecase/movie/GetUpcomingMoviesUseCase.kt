package com.netflix.clone.domain.usecase.movie

import androidx.paging.PagingData
import androidx.paging.map
import com.netflix.clone.data.remote.mapper.toMovieResultModel
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetUpcomingMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<MovieResultModel>> =
        movieRepository.getUpcomingMovies(language, page, region).map { pagingData ->
            pagingData.map {
                it.toMovieResultModel()
            }
        }
}
