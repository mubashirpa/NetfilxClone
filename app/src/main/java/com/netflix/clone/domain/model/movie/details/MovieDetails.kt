package com.netflix.clone.domain.model.movie.details

import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.model.movie.credits.MovieCredits

data class MovieDetails(
    val backdropPath: String? = null,
    val genres: List<MovieGenre>? = null,
    val id: Int? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val releaseDate: String? = null,
    val runtime: Int? = null,
    val title: String? = null,
    val voteAverage: Double? = null,
    val credits: MovieCredits? = null,
    val recommendations: List<MovieResultModel>? = null,
)
