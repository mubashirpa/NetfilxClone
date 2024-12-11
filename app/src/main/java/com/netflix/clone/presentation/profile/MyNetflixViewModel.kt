package com.netflix.clone.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.netflix.clone.domain.usecase.GetListUseCase
import com.netflix.clone.domain.usecase.GetNowPlayingMoviesUseCase
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class MyNetflixViewModel(
    private val getListUseCase: GetListUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(MyNetflixUiState())
        private set

    init {
        getMyList()
        getNowPlayingMovies()
    }

    private fun getMyList() {
        viewModelScope.launch {
            getListUseCase(8257364)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    uiState.myList.value = it
                }
        }
    }

    private fun getNowPlayingMovies() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    uiState.nowPlayingMovies.value = it
                }
        }
    }
}
