package com.netflix.clone.domain.usecase

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.remote.mapper.toTrending
import com.netflix.clone.data.remote.mapper.toTrendingEntity
import com.netflix.clone.domain.model.trending.Trending
import com.netflix.clone.domain.repository.MovieRepository
import com.netflix.clone.domain.repository.TimeWindow
import com.netflix.clone.domain.repository.TrendingLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetTrendingUseCase(
    private val movieRepository: MovieRepository,
    private val trendingLocalRepository: TrendingLocalRepository,
) {
    operator fun invoke(
        timeWindow: TimeWindow = TimeWindow.DAY,
        language: String = "en-US",
    ): Flow<Resource<List<Trending>>> =
        flow {
            try {
                emit(Resource.Loading())
                val trending =
                    if (trendingLocalRepository.isCacheValid()) {
                        trendingLocalRepository.getCachedTrending().map { it.toTrending() }
                    } else {
                        val results =
                            movieRepository
                                .getTrending(timeWindow, language)
                                .results
                                .orEmpty()
                                .map { it.toTrending() }
                        trendingLocalRepository.clearCache()
                        trendingLocalRepository.saveTrending(results.map { it.toTrendingEntity() })
                        results
                    }
                emit(Resource.Success(trending))
            } catch (_: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (_: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (_: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
