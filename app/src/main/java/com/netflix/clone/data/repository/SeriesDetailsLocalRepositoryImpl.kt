package com.netflix.clone.data.repository

import com.netflix.clone.data.local.database.NetflixDatabase
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsEntity
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsWithSeasons
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationEntity
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonEntity
import com.netflix.clone.domain.repository.SeriesDetailsLocalRepository

class SeriesDetailsLocalRepositoryImpl(
    database: NetflixDatabase,
) : SeriesDetailsLocalRepository {
    private val seriesDetailsDao = database.seriesDetailsDao()

    override suspend fun getSeriesDetailsById(id: Int): SeriesDetailsWithSeasons? = seriesDetailsDao.getSeriesDetailsWithSeasons(id)

    override suspend fun clearCacheById(id: Int) {
        seriesDetailsDao.deleteSeriesDetailsWithCrossRefs(id)
    }

    override suspend fun saveSeriesDetails(
        seriesDetails: SeriesDetailsEntity,
        seriesSeasons: List<SeriesSeasonEntity>,
        seriesRecommendations: List<SeriesRecommendationEntity>,
        seriesSeasonCrossRef: List<SeriesSeasonCrossRef>,
        seriesRecommendationCrossRef: List<SeriesRecommendationCrossRef>,
    ) {
        seriesDetailsDao.upsertMovieDetailsWithRecommendations(
            seriesDetails = seriesDetails,
            seriesSeasons = seriesSeasons,
            seriesRecommendations = seriesRecommendations,
            seriesSeasonCrossRef = seriesSeasonCrossRef,
            seriesRecommendationCrossRef = seriesRecommendationCrossRef,
        )
    }
}
