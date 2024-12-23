package com.netflix.clone.presentation.movie

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.MovieDetails

data class MovieUiState(
    val movieDetailsResource: Resource<MovieDetails> = Resource.Empty(),
)
