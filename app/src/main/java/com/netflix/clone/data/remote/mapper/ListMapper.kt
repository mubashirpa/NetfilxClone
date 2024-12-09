package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.list.ListResult
import com.netflix.clone.domain.model.list.ListResultModel

fun ListResult.toListResultModel(): ListResultModel = ListResultModel(id, mediaType, posterPath)
