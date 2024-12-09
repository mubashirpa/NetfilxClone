package com.netflix.clone.domain.usecase.person

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toPersonDetails
import com.netflix.clone.domain.model.person.PersonDetails
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetPersonDetailsUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        personId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): Flow<Resource<PersonDetails>> =
        flow {
            try {
                emit(Resource.Loading())
                val details =
                    movieRepository
                        .getPersonDetails(personId, appendToResponse, language)
                        .toPersonDetails()
                emit(Resource.Success(details))
            } catch (e: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (e: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (e: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
