package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.local.entity.movies.MovieEntity
import com.netflix.clone.data.local.entity.movies.NowPlayingMoviesEntity
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.local.entity.movies.UpcomingMoviesEntity
import com.netflix.clone.data.local.entity.movies.details.MovieDetailsEntity
import com.netflix.clone.data.local.entity.movies.details.MovieDetailsWithRecommendations
import com.netflix.clone.data.local.entity.movies.details.MovieRecommendationEntity
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

fun PopularMoviesEntity.toMovie(): Movie =
    Movie(
        backdropPath = movie.backdropPath,
        id = movie.movieId,
        overview = movie.overview,
        posterPath = movie.posterPath,
        title = movie.title,
    )

fun MovieResult.toUpcomingMoviesEntity(): UpcomingMoviesEntity =
    UpcomingMoviesEntity(
        movie =
            MovieEntity(
                backdropPath = backdropPath,
                movieId = id,
                overview = overview,
                posterPath = posterPath,
                title = title,
            ),
    )

fun UpcomingMoviesEntity.toMovie(): Movie =
    Movie(
        backdropPath = movie.backdropPath,
        id = movie.movieId,
        overview = movie.overview,
        posterPath = movie.posterPath,
        title = movie.title,
    )

fun MovieResult.toNowPlayingMoviesEntity(): NowPlayingMoviesEntity =
    NowPlayingMoviesEntity(
        movie =
            MovieEntity(
                backdropPath = backdropPath,
                movieId = id,
                overview = overview,
                posterPath = posterPath,
                title = title,
            ),
    )

fun NowPlayingMoviesEntity.toMovie(): Movie =
    Movie(
        backdropPath = movie.backdropPath,
        id = movie.movieId,
        overview = movie.overview,
        posterPath = movie.posterPath,
        title = movie.title,
    )

fun MovieDetailsWithRecommendations.toMovieDetails(): MovieDetails =
    MovieDetails(
        backdropPath = movieDetails.backdropPath,
        cast = movieDetails.cast,
        directors = movieDetails.directors,
        genres = movieDetails.genres,
        id = movieDetails.movieId,
        overview = movieDetails.overview,
        posterPath = movieDetails.posterPath,
        recommendations = recommendations.map { it.toMovie() },
        releaseDate = movieDetails.releaseDate,
        runtime = movieDetails.runtime,
        title = movieDetails.title,
    )

fun MovieRecommendationEntity.toMovie(): Movie =
    Movie(
        backdropPath = movie.backdropPath,
        id = movie.movieId,
        overview = movie.overview,
        posterPath = movie.posterPath,
        title = movie.title,
    )

fun MovieDetails.toMovieDetailsWithRecommendations(lastUpdated: Long): MovieDetailsWithRecommendations =
    MovieDetailsWithRecommendations(
        movieDetails =
            MovieDetailsEntity(
                movieId = id!!,
                backdropPath = backdropPath,
                cast = cast,
                directors = directors,
                genres = genres,
                lastUpdated = lastUpdated,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                runtime = runtime,
                title = title,
            ),
        recommendations = recommendations?.map { it.toRecommendationEntity() } ?: emptyList(),
    )

fun Movie.toRecommendationEntity(): MovieRecommendationEntity =
    MovieRecommendationEntity(
        recommendationId = id!!,
        movie =
            MovieEntity(
                backdropPath = backdropPath,
                movieId = id,
                overview = overview,
                posterPath = posterPath,
                title = title,
            ),
    )
