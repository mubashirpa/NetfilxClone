package com.netflix.clone.domain.model.series.credits

data class SeriesCredits(
    val cast: List<SeriesCast>? = null,
    val crew: List<SeriesCrew>? = null,
)
