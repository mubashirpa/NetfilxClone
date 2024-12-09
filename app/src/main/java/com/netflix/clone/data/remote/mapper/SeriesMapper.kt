package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.series.SeriesResult
import com.netflix.clone.data.remote.dto.series.contentRating.ContentRating
import com.netflix.clone.data.remote.dto.series.contentRating.ContentRatingResult
import com.netflix.clone.data.remote.dto.series.credits.Cast
import com.netflix.clone.data.remote.dto.series.credits.Crew
import com.netflix.clone.data.remote.dto.series.credits.SeriesCreditsDto
import com.netflix.clone.data.remote.dto.series.details.CreatedBy
import com.netflix.clone.data.remote.dto.series.details.Genre
import com.netflix.clone.data.remote.dto.series.details.Season
import com.netflix.clone.data.remote.dto.series.details.SeriesDetailsDto
import com.netflix.clone.domain.model.series.SeriesResultModel
import com.netflix.clone.domain.model.series.contentRating.ContentRatingModel
import com.netflix.clone.domain.model.series.contentRating.ContentRatingResultModel
import com.netflix.clone.domain.model.series.credits.SeriesCast
import com.netflix.clone.domain.model.series.credits.SeriesCredits
import com.netflix.clone.domain.model.series.credits.SeriesCrew
import com.netflix.clone.domain.model.series.details.SeriesCreatedBy
import com.netflix.clone.domain.model.series.details.SeriesDetails
import com.netflix.clone.domain.model.series.details.SeriesGenre
import com.netflix.clone.domain.model.series.details.SeriesSeason

fun SeriesDetailsDto.toSeriesDetails(): SeriesDetails =
    SeriesDetails(
        backdropPath,
        createdBy?.map { it.toSeriesCreatedBy() },
        firstAirDate,
        genres?.map { it.toSeriesGenre() },
        id,
        name,
        numberOfEpisodes,
        numberOfSeasons,
        overview,
        seasons?.map { it.toSeriesSeason() },
        voteAverage,
        contentRatings?.toContentRatingModel(),
        credits?.toSeriesCredits(),
        recommendations?.results?.map { it.toSeriesResultModel() },
    )

fun CreatedBy.toSeriesCreatedBy(): SeriesCreatedBy = SeriesCreatedBy(name)

fun Genre.toSeriesGenre(): SeriesGenre = SeriesGenre(name)

fun Season.toSeriesSeason(): SeriesSeason =
    SeriesSeason(
        airDate,
        episodeCount,
        id,
        name,
        overview,
        posterPath,
        seasonNumber,
        voteAverage,
    )

fun ContentRating.toContentRatingModel(): ContentRatingModel = ContentRatingModel(results?.map { it.toContentRatingResultModel() })

fun ContentRatingResult.toContentRatingResultModel(): ContentRatingResultModel = ContentRatingResultModel(iso31661, rating)

fun SeriesCreditsDto.toSeriesCredits(): SeriesCredits = SeriesCredits(cast?.map { it.toSeriesCast() }, crew?.map { it.toSeriesCrew() })

fun Cast.toSeriesCast(): SeriesCast = SeriesCast(character, id, name, profilePath)

fun Crew.toSeriesCrew(): SeriesCrew = SeriesCrew(id, job, name, profilePath)

fun SeriesResult.toSeriesResultModel(): SeriesResultModel = SeriesResultModel(firstAirDate, id, name, posterPath, voteAverage)
