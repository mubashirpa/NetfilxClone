package com.netflix.clone.presentation.search

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.Game
import com.netflix.clone.domain.model.trending.TrendingResultModel

data class SearchUiState(
    val recommendedGames: List<Game> = emptyList(),
    val trendingResource: Resource<List<TrendingResultModel>> = Resource.Empty(),
)