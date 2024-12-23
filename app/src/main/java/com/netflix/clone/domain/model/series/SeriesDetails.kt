package com.netflix.clone.domain.model.series

data class SeriesDetails(
    val backdropPath: String? = null,
    val casts: String? = null,
    val createdBy: String? = null,
    val firstAirDate: String? = null,
    val genres: String? = null,
    val id: Int? = null,
    val name: String? = null,
    val numberOfSeasons: Int? = null,
    val overview: String? = null,
    val recommendations: List<Series>? = null,
    val seasons: List<SeriesSeason>? = null,
)
