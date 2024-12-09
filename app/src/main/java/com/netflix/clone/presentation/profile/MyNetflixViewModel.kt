package com.netflix.clone.presentation.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netflix.clone.domain.usecase.GetListUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MyNetflixViewModel(
    private val getListUseCase: GetListUseCase,
) : ViewModel() {
    var uiState by mutableStateOf(MyNetflixUiState())
        private set

    init {
        getMyList()
    }

    private fun getMyList() {
        getListUseCase(8257364)
            .onEach { resource ->
                uiState = uiState.copy(myListResource = resource)
            }.launchIn(viewModelScope)
    }
}
