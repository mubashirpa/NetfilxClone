package com.netflix.clone.presentation.profile

import androidx.paging.PagingData
import com.netflix.clone.domain.model.list.UserList
import com.netflix.clone.domain.model.movie.Movie
import kotlinx.coroutines.flow.MutableStateFlow

data class MyNetflixUiState(
    val myList: MutableStateFlow<PagingData<UserList>> =
        MutableStateFlow(PagingData.empty()),
    val nowPlayingMovies: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty()),
)
