package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.local.entity.movies.MovieEntity
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.remote.dto.movie.MovieResult
import com.netflix.clone.data.remote.dto.movie.details.MovieDetailsDto
import com.netflix.clone.domain.model.movie.Movie
import com.netflix.clone.domain.model.movie.MovieDetails

fun MovieDetailsDto.toMovieDetails(): MovieDetails =
    MovieDetails(
        backdropPath = backdropPath,
        cast = credits?.cast?.joinToString(", ") { it.name.orEmpty() },
        directors =
            credits
                ?.crew
                ?.filter { it.job == "Director" }
                ?.joinToString(", ") { it.name.orEmpty() },
        genres = genres?.joinToString(", ") { it.name.orEmpty() },
        id = id,
        overview = overview,
        posterPath = posterPath,
        recommendations = recommendations?.results?.map { it.toMovie() },
        releaseDate = releaseDate,
        runtime = runtime,
        title = title,
    )

fun MovieResult.toMovie(): Movie =
    Movie(
        backdropPath = backdropPath,
        id = id,
        overview = overview,
        posterPath = posterPath,
        title = title,
    )

fun MovieResult.toPopularMoviesEntity(): PopularMoviesEntity =
    PopularMoviesEntity(
        movie =
            MovieEntity(
                backdropPath = backdropPath,
                movieId = id,
                overview = overview,
                posterPath = posterPath,
                title = title,
            ),
    )

fun PopularMoviesEntity.toMovieResultModel(): Movie =
    movie.let { movie ->
        Movie(
            backdropPath = movie.backdropPath,
            id = movie.movieId,
            overview = movie.overview,
            posterPath = movie.posterPath,
            title = movie.title,
        )
    }
