package com.netflix.clone.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsEntity
import com.netflix.clone.data.local.entity.series.details.SeriesDetailsWithSeasons
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesRecommendationEntity
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonCrossRef
import com.netflix.clone.data.local.entity.series.details.SeriesSeasonEntity

@Dao
interface SeriesDetailsDao {
    @Upsert
    suspend fun upsertSeriesDetails(seriesDetails: SeriesDetailsEntity)

    @Upsert
    suspend fun upsertSeriesSeasons(seriesSeasons: List<SeriesSeasonEntity>)

    @Upsert
    suspend fun upsertSeriesRecommendations(seriesRecommendations: List<SeriesRecommendationEntity>)

    @Upsert
    suspend fun upsertSeriesSeasonCrossRef(seriesSeasonCrossRef: List<SeriesSeasonCrossRef>)

    @Upsert
    suspend fun upsertSeriesRecommendationCrossRef(seriesRecommendationCrossRef: List<SeriesRecommendationCrossRef>)

    @Query("DELETE FROM series_details WHERE seriesId = :seriesId")
    suspend fun deleteSeriesDetails(seriesId: Int)

    @Query("DELETE FROM series_seasons WHERE seriesId = :seriesId")
    suspend fun deleteSeriesSeasons(seriesId: Int)

    @Query("DELETE FROM SeriesSeasonCrossRef WHERE seriesId = :seriesId")
    suspend fun deleteSeriesSeasonCrossRef(seriesId: Int)

    @Query("DELETE FROM SeriesRecommendationCrossRef WHERE seriesId = :seriesId")
    suspend fun deleteSeriesRecommendationCrossRef(seriesId: Int)

    @Transaction
    @Query("SELECT * FROM series_details WHERE seriesId = :seriesId")
    suspend fun getSeriesDetailsWithSeasons(seriesId: Int): SeriesDetailsWithSeasons?

    @Transaction
    suspend fun upsertMovieDetailsWithRecommendations(
        seriesDetails: SeriesDetailsEntity,
        seriesSeasons: List<SeriesSeasonEntity>,
        seriesRecommendations: List<SeriesRecommendationEntity>,
        seriesSeasonCrossRef: List<SeriesSeasonCrossRef>,
        seriesRecommendationCrossRef: List<SeriesRecommendationCrossRef>,
    ) {
        upsertSeriesDetails(seriesDetails)
        upsertSeriesSeasons(seriesSeasons)
        upsertSeriesRecommendations(seriesRecommendations)
        upsertSeriesSeasonCrossRef(seriesSeasonCrossRef)
        upsertSeriesRecommendationCrossRef(seriesRecommendationCrossRef)
    }

    @Transaction
    suspend fun deleteSeriesDetailsWithCrossRefs(id: Int) {
        deleteSeriesRecommendationCrossRef(id)
        deleteSeriesSeasonCrossRef(id)
        deleteSeriesSeasons(id)
        deleteSeriesDetails(id)
    }
}
