package com.netflix.clone.presentation.content

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.details.MovieDetails

data class MovieUiState(
    val movieDetailsResource: Resource<MovieDetails> = Resource.Empty(),
)
