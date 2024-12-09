package com.netflix.clone.domain.usecase.person

import androidx.paging.PagingData
import androidx.paging.map
import com.netflix.clone.data.remote.mapper.toPersonResultModel
import com.netflix.clone.domain.model.person.popular.PersonResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPopularPersonUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<PersonResultModel>> =
        movieRepository
            .getPopularPerson(language, page)
            .map { pagingData ->
                pagingData.map {
                    it.toPersonResultModel()
                }
            }
}
