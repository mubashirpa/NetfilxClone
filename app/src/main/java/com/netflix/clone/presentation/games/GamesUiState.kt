package com.netflix.clone.presentation.games

import com.netflix.clone.domain.model.Game

data class GamesUiState(
    val popularGames: List<Game> = emptyList(),
    val mobileGames: List<Game> = emptyList(),
)
