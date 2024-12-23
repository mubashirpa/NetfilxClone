package com.netflix.clone.domain.usecase

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toTrending
import com.netflix.clone.domain.model.trending.Trending
import com.netflix.clone.domain.repository.MovieRepository
import com.netflix.clone.domain.repository.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetTrendingUseCase(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(
        timeWindow: TimeWindow = TimeWindow.DAY,
        language: String = "en-US",
    ): Flow<Resource<List<Trending>>> =
        flow {
            try {
                emit(Resource.Loading())
                val results =
                    movieRepository
                        .getTrending(
                            timeWindow,
                            language,
                        ).results
                        ?.map { it.toTrending() }
                emit(Resource.Success(results))
            } catch (_: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (_: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (_: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
