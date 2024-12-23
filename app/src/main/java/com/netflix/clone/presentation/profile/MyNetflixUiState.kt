package com.netflix.clone.presentation.profile

import androidx.paging.PagingData
import com.netflix.clone.domain.model.list.ListResultModel
import com.netflix.clone.domain.model.movie.Movie
import kotlinx.coroutines.flow.MutableStateFlow

data class MyNetflixUiState(
    val myList: MutableStateFlow<PagingData<ListResultModel>> =
        MutableStateFlow(PagingData.empty()),
    val nowPlayingMovies: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty()),
)
