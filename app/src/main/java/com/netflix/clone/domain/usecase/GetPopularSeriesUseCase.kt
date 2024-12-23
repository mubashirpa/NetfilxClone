package com.netflix.clone.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.netflix.clone.data.remote.mapper.toSeries
import com.netflix.clone.domain.model.series.Series
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularSeriesUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<Series>> =
        movieRepository.getPopularSeries(language, page).map { pagingData ->
            pagingData.map {
                it.toSeries()
            }
        }
}
