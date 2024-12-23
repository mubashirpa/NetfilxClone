package com.netflix.clone.presentation.news

import androidx.paging.PagingData
import com.netflix.clone.domain.model.movie.Movie
import kotlinx.coroutines.flow.MutableStateFlow

data class NewsAndHotUiState(
    val upcomingMovies: MutableStateFlow<PagingData<Movie>> = MutableStateFlow(PagingData.empty()),
)
