package com.netflix.clone.presentation.home

import androidx.paging.PagingData
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.model.series.SeriesResultModel
import com.netflix.clone.domain.model.trending.TrendingResultModel
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeUiState(
    val popularMoviesResource: Resource<List<MovieResultModel>> = Resource.Empty(),
    val trendingResource: Resource<List<TrendingResultModel>> = Resource.Empty(),
    val popularSeries: MutableStateFlow<PagingData<SeriesResultModel>> = MutableStateFlow(PagingData.empty()),
)
