package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.search.SearchResult
import com.netflix.clone.domain.model.search.SearchResultModel

fun SearchResult.toSearchResultModel(): SearchResultModel =
    SearchResultModel(
        firstAirDate,
        genreIds,
        id,
        knownFor,
        knownForDepartment,
        mediaType,
        name,
        posterPath,
        profilePath,
        releaseDate,
        title,
        voteAverage,
    )
