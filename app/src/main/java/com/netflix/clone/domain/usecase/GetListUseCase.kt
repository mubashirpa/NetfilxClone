package com.netflix.clone.domain.usecase

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toListResultModel
import com.netflix.clone.domain.model.list.ListResultModel
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetListUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(listId: Int): Flow<Resource<List<ListResultModel>>> =
        flow {
            try {
                emit(Resource.Loading())
                val results =
                    movieRepository.getList(listId).results?.map { it.toListResultModel() }
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
