package com.netflix.clone.presentation.profile

import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.list.ListResultModel

data class MyNetflixUiState(
    val myListResource: Resource<List<ListResultModel>> = Resource.Empty(),
)
