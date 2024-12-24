package com.netflix.clone.data.remote.mapper

import com.netflix.clone.data.local.entity.list.UserListEntity
import com.netflix.clone.data.remote.dto.list.ListResult
import com.netflix.clone.domain.model.list.UserList

fun ListResult.toUserListEntity(): UserListEntity =
    UserListEntity(
        listId = id,
        mediaType = mediaType,
        posterPath = posterPath,
    )

fun UserListEntity.toUserList(): UserList =
    UserList(
        id = listId,
        mediaType = mediaType,
        posterPath = posterPath,
    )
