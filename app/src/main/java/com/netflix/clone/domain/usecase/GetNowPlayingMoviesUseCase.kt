package com.netflix.clone.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.netflix.clone.data.remote.mapper.toMovie
import com.netflix.clone.domain.model.movie.Movie
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetNowPlayingMoviesUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        language: String = "en-US",
        page: Int = 1,
        region: String? = null,
    ): Flow<PagingData<Movie>> =
        movieRepository.getNowPlayingMovies(language, page, region).map { pagingData ->
            pagingData.map {
                it.toMovie()
            }
        }
}
