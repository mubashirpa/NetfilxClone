package com.netflix.clone.presentation.news

import androidx.paging.PagingData
import com.netflix.clone.domain.model.movie.MovieResultModel
import kotlinx.coroutines.flow.MutableStateFlow

data class NewsAndHotUiState(
    val upcomingMovies: MutableStateFlow<PagingData<MovieResultModel>> = MutableStateFlow(PagingData.empty()),
)
