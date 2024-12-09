package com.netflix.clone.domain.usecase.search

import androidx.paging.PagingData
import androidx.paging.map
import com.netflix.clone.data.remote.mapper.toSearchResultModel
import com.netflix.clone.domain.model.search.SearchResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchMultiPagingUseCase(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        query: String,
        includeAdult: Boolean = false,
        language: String = "en-US",
        page: Int = 1,
    ): Flow<PagingData<SearchResultModel>> =
        movieRepository
            .searchMultiPaging(query, includeAdult, language, page)
            .map { pagingData ->
                pagingData.map {
                    it.toSearchResultModel()
                }
            }
}
