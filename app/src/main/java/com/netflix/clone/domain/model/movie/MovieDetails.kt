package com.netflix.clone.domain.model.movie

data class MovieDetails(
    val backdropPath: String? = null,
    val cast: String? = null,
    val directors: String? = null,
    val genres: String? = null,
    val id: Int? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val recommendations: List<Movie>? = null,
    val releaseDate: String? = null,
    val runtime: Int? = null,
    val title: String? = null,
)
