package com.netflix.clone.presentation.home

import androidx.paging.PagingData
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.model.series.SeriesResultModel
import com.netflix.clone.domain.model.trending.TrendingResultModel
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeUiState(
    val popularMovies: MutableStateFlow<PagingData<MovieResultModel>> =
        MutableStateFlow(PagingData.empty()),
    val trendingResource: Resource<List<TrendingResultModel>> = Resource.Empty(),
    val popularSeries: MutableStateFlow<PagingData<SeriesResultModel>> =
        MutableStateFlow(PagingData.empty()),
    val topRatedSeries: MutableStateFlow<PagingData<SeriesResultModel>> =
        MutableStateFlow(PagingData.empty()),
)
