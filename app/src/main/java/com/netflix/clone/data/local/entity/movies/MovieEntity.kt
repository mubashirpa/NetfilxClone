package com.netflix.clone.data.local.entity.movies

data class MovieEntity(
    val backdropPath: String?,
    val movieId: Int?,
    val overview: String?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val voteAverage: Double?,
)
