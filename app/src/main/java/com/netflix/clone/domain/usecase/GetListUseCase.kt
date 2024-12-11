package com.netflix.clone.domain.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.netflix.clone.data.remote.mapper.toListResultModel
import com.netflix.clone.domain.model.list.ListResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetListUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        listId: Int,
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<ListResultModel>> =
        movieRepository.getList(listId, language, page).map { pagingData ->
            pagingData.map {
                it.toListResultModel()
            }
        }
}
