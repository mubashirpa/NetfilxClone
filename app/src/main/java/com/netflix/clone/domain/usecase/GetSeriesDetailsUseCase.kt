package com.netflix.clone.domain.usecase

import com.netflix.clone.core.utils.CacheUtils
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.core.utils.UiText
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonCrossRef
import com.netflix.clone.data.remote.mapper.toSeriesDetails
import com.netflix.clone.data.remote.mapper.toSeriesDetailsWithSeasons
import com.netflix.clone.domain.model.series.SeriesDetails
import com.netflix.clone.domain.repository.MovieRepository
import com.netflix.clone.domain.repository.SeriesDetailsLocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import com.netflix.clone.R.string as Strings

class GetSeriesDetailsUseCase(
    private val movieRepository: MovieRepository,
    private val seriesDetailsLocalRepository: SeriesDetailsLocalRepository,
) {
    operator fun invoke(
        seriesId: Int,
        appendToResponse: String? = null,
        language: String = "en-US",
    ): Flow<Resource<SeriesDetails>> =
        flow {
            try {
                emit(Resource.Loading())

                val cachedData = seriesDetailsLocalRepository.getSeriesDetailsById(seriesId)
                val lastUpdated = cachedData?.seriesDetails?.lastUpdated ?: 0

                if (cachedData != null && CacheUtils.isCacheValid(lastUpdated)) {
                    emit(Resource.Success(cachedData.toSeriesDetails()))
                    return@flow
                }

                val remoteDetails =
                    movieRepository
                        .getSeriesDetails(seriesId, appendToResponse, language)
                        .toSeriesDetails()
                val seriesDetailsWithSeasons =
                    remoteDetails.toSeriesDetailsWithSeasons(
                        System.currentTimeMillis(),
                    )

                seriesDetailsLocalRepository.saveSeriesDetails(
                    seriesDetails = seriesDetailsWithSeasons.seriesDetails,
                    seriesSeasons = seriesDetailsWithSeasons.seasons,
                    seriesSeasonCrossRef =
                        seriesDetailsWithSeasons.seasons.map {
                            SeriesSeasonCrossRef(
                                seriesId = seriesDetailsWithSeasons.seriesDetails.seriesId,
                                seasonId = it.seasonId,
                            )
                        },
                )

                emit(Resource.Success(remoteDetails))
            } catch (_: IOException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_no_internet)))
            } catch (_: HttpException) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unexpected)))
            } catch (_: Exception) {
                emit(Resource.Error(UiText.StringResource(Strings.error_unknown)))
            }
        }
}
