package com.netflix.clone.presentation.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.netflix.clone.domain.usecase.movie.GetMovieDetailsUseCase
import com.netflix.clone.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MovieViewModel(
    savedStateHandle: SavedStateHandle,
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(MovieUiState())
        private set

    private val id: Int = savedStateHandle.toRoute<Screen.MovieScreen>().id

    init {
        getMovieDetails(id)
    }

    private fun getMovieDetails(id: Int) {
        getMovieDetailsUseCase(
            movieId = id,
            appendToResponse = "credits,recommendations",
        ).onEach { resource ->
            uiState = uiState.copy(movieDetailsResource = resource)
        }.launchIn(viewModelScope)
    }
}
