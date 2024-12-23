package com.netflix.clone.presentation.home

import androidx.paging.PagingData
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.Movie
import com.netflix.clone.domain.model.series.Series
import com.netflix.clone.domain.model.trending.Trending
import kotlinx.coroutines.flow.MutableStateFlow

data class HomeUiState(
    val popularMovies: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(PagingData.empty()),
    val trendingResource: Resource<List<Trending>> = Resource.Empty(),
    val popularSeries: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty()),
    val topRatedSeries: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty()),
)
