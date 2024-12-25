package com.netflix.clone.data.local.entity.series.details

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SeriesDetailsWithSeasons(
    @Embedded val seriesDetails: SeriesDetailsEntity,
    @Relation(
        parentColumn = "seriesId",
        entityColumn = "recommendationId",
        associateBy = Junction(SeriesRecommendationCrossRef::class),
    )
    val recommendations: List<SeriesRecommendationEntity>,
    @Relation(
        parentColumn = "seriesId",
        entityColumn = "seasonId",
        associateBy = Junction(SeriesSeasonCrossRef::class),
    )
    val seasons: List<SeriesSeasonEntity>,
)
