package com.netflix.clone.presentation.tv

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.series.SeriesDetails

data class TvUiState(
    val tvDetailsResource: Resource<SeriesDetails> = Resource.Empty(),
)
