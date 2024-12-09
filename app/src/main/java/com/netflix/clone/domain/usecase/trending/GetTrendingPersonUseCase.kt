package com.netflix.clone.domain.usecase.trending

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toTrendingPersonResultModel
import com.netflix.clone.domain.model.trending.TrendingPersonResultModel
import com.netflix.clone.domain.repository.MovieRepository
import com.netflix.clone.domain.repository.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetTrendingPersonUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        timeWindow: TimeWindow = TimeWindow.DAY,
        language: String = "en-US",
    ): Flow<Resource<List<TrendingPersonResultModel>>> =
        flow {
            try {
                emit(Resource.Loading())
                val results =
                    movieRepository
                        .getTrendingPerson(
                            timeWindow,
                            language,
                        ).results
                        ?.map { it.toTrendingPersonResultModel() }
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
