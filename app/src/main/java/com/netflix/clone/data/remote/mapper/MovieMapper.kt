package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.local.entity.movies.MovieEntity
import com.netflix.clone.data.local.entity.movies.PopularMoviesEntity
import com.netflix.clone.data.remote.dto.movie.MovieResult
import com.netflix.clone.data.remote.dto.movie.credits.Cast
import com.netflix.clone.data.remote.dto.movie.credits.Crew
import com.netflix.clone.data.remote.dto.movie.credits.MovieCreditsDto
import com.netflix.clone.data.remote.dto.movie.details.Genre
import com.netflix.clone.data.remote.dto.movie.details.MovieDetailsDto
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.model.movie.credits.MovieCast
import com.netflix.clone.domain.model.movie.credits.MovieCredits
import com.netflix.clone.domain.model.movie.credits.MovieCrew
import com.netflix.clone.domain.model.movie.details.MovieDetails
import com.netflix.clone.domain.model.movie.details.MovieGenre

fun MovieDetailsDto.toMovieDetails(): MovieDetails =
    MovieDetails(
        backdropPath,
        genres?.map { it.toMovieGenre() },
        id,
        overview,
        posterPath,
        releaseDate,
        runtime,
        title,
        voteAverage,
        credits?.toMovieCredits(),
        recommendations?.results?.map { it.toMovieResultModel() },
    )

fun Genre.toMovieGenre(): MovieGenre = MovieGenre(name)

fun MovieCreditsDto.toMovieCredits(): MovieCredits = MovieCredits(cast?.map { it.toMovieCast() }, crew?.map { it.toMovieCrew() })

fun Cast.toMovieCast(): MovieCast = MovieCast(character, id, name, profilePath)

fun Crew.toMovieCrew(): MovieCrew = MovieCrew(id, job, name, profilePath)

fun MovieResult.toMovieResultModel(): MovieResultModel =
    MovieResultModel(backdropPath, id, overview, posterPath, releaseDate, title, voteAverage)

fun MovieResult.toPopularMoviesEntity(): PopularMoviesEntity =
    PopularMoviesEntity(
        movie =
            MovieEntity(
                backdropPath = backdropPath,
                movieId = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
            ),
    )

fun PopularMoviesEntity.toMovieResultModel(): MovieResultModel =
    movie.let { movie ->
        MovieResultModel(
            backdropPath = movie.backdropPath,
            id = movie.movieId,
            overview = movie.overview,
            posterPath = movie.posterPath,
            releaseDate = movie.releaseDate,
            title = movie.title,
            voteAverage = movie.voteAverage,
        )
    }
