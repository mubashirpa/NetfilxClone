package com.netflix.clone.domain.usecase.series

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toSeriesDetails
import com.netflix.clone.domain.model.series.details.SeriesDetails
import com.netflix.clone.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetSeriesDetailsUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        seriesId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): Flow<Resource<SeriesDetails>> =
        flow {
            try {
                emit(Resource.Loading())
                val details =
                    movieRepository
                        .getSeriesDetails(seriesId, appendToResponse, language)
                        .toSeriesDetails()
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
