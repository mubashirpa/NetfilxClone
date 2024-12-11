package com.netflix.clone.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data object Games : Screen()

    @Serializable
    data object NewsAndHot : Screen()

    @Serializable
    data object MyNetflix : Screen()

    @Serializable
    data object Search : Screen()

    @Serializable
    data class MovieScreen(
        val id: Int,
    ) : Screen()

    @Serializable
    data class TvScreen(
        val id: Int,
    ) : Screen()
}
