package com.netflix.clone.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.netflix.clone.domain.usecase.movie.GetPopularMoviesUseCase
import com.netflix.clone.domain.usecase.series.GetPopularSeriesUseCase
import com.netflix.clone.domain.usecase.series.GetTopRatedSeriesUseCase
import com.netflix.clone.domain.usecase.trending.GetTrendingUseCase
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.Locale

class HomeViewModel(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTrendingUseCase: GetTrendingUseCase,
    private val getPopularSeriesUseCase: GetPopularSeriesUseCase,
    private val getTopRatedSeriesUseCase: GetTopRatedSeriesUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        getPopularMovies()
        getTrending()
        getPopularSeries()
        getTopRatedSeries()
    }

    private fun getPopularMovies() {
        val locale = Locale.getDefault()
        getPopularMoviesUseCase(region = locale.country)
            .onEach { resource ->
                uiState = uiState.copy(popularMoviesResource = resource)
            }.launchIn(viewModelScope)
    }

    private fun getTrending() {
        getTrendingUseCase()
            .onEach { resource ->
                uiState = uiState.copy(trendingResource = resource)
            }.launchIn(viewModelScope)
    }

    private fun getPopularSeries() {
        viewModelScope.launch {
            getPopularSeriesUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    uiState.popularSeries.value = it
                }
        }
    }

    private fun getTopRatedSeries() {
        viewModelScope.launch {
            getTopRatedSeriesUseCase()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
                .collect {
                    uiState.topRatedSeries.value = it
                }
        }
    }
}
