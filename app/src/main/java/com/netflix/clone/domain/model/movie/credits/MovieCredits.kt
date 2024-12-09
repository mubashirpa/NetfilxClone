package com.netflix.clone.domain.model.movie.credits

data class MovieCredits(
    val cast: List<MovieCast>? = null,
    val crew: List<MovieCrew>? = null,
)
