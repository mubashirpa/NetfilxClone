package com.netflix.clone.presentation.tv

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.netflix.clone.domain.usecase.series.GetSeriesDetailsUseCase
import com.netflix.clone.navigation.Screen
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class TvViewModel(
    savedStateHandle: SavedStateHandle,
    private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(TvUiState())
        private set

    private val id: Int = savedStateHandle.toRoute<Screen.TvScreen>().id

    init {
        getTvDetails(id)
    }

    private fun getTvDetails(seriesId: Int) {
        getSeriesDetailsUseCase(
            seriesId,
            appendToResponse = "content_ratings,credits,recommendations",
        ).onEach { resource ->
            uiState = uiState.copy(tvDetailsResource = resource)
        }.launchIn(viewModelScope)
    }
}
