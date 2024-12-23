package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.remote.dto.list.ListResult
import com.netflix.clone.domain.model.list.UserList

fun ListResult.toUserList(): UserList =
    UserList(
        id = id,
        mediaType = mediaType,
        posterPath = posterPath,
    )
