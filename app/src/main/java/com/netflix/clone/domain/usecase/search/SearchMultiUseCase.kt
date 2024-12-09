package com.netflix.clone.domain.usecase.search

import com.netflix.clone.R
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toSearchResultModel
import com.netflix.clone.domain.model.search.SearchResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class SearchMultiUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        query: String,
        includeAdult: Boolean = false,
        language: String = "en-US",
        page: Int = 1,
    ): Flow<Resource<List<SearchResultModel>>> =
        flow {
            try {
                emit(Resource.Loading())
                val results =
                    movieRepository
                        .searchMulti(
                            query,
                            includeAdult,
                            language,
                            page,
                        ).results
                        ?.map { it.toSearchResultModel() }
                emit(Resource.Success(results))
            } catch (e: IOException) {
                emit(Resource.Error(UiText.StringResource(R.string.error_no_internet)))
            } catch (e: HttpException) {
                emit(Resource.Error(UiText.StringResource(R.string.error_unexpected)))
            } catch (e: Exception) {
                emit(Resource.Error(UiText.StringResource(R.string.error_unknown)))
            }
        }
}
