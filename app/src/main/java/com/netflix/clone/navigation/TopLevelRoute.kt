package com.netflix.clone.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.netflix.clone.R

data class TopLevelRoute<T : Any>(
    @StringRes val labelId: Int,
    val route: T,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
)

val topLevelRoutes =
    listOf(
        TopLevelRoute(
            labelId = R.string.label_home,
            route = Screen.Home,
            selectedIcon = R.drawable.baseline_home_24px,
            unselectedIcon = R.drawable.outline_home_24px,
        ),
        TopLevelRoute(
            labelId = R.string.label_games,
            route = Screen.Games,
            selectedIcon = R.drawable.baseline_stadia_controller_24px,
            unselectedIcon = R.drawable.outline_stadia_controller_24px,
        ),
        TopLevelRoute(
            labelId = R.string.label_news_and_hot,
            route = Screen.NewsAndHot,
            selectedIcon = R.drawable.baseline_animated_images_24px,
            unselectedIcon = R.drawable.outline_animated_images_24px,
        ),
    )
