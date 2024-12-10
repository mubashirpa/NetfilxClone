package com.netflix.clone.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.netflix.clone.R
import com.netflix.clone.presentation.content.MovieScreen
import com.netflix.clone.presentation.content.MovieViewModel
import com.netflix.clone.presentation.home.HomeScreen
import com.netflix.clone.presentation.home.HomeViewModel
import com.netflix.clone.presentation.news.NewsAndHotScreen
import com.netflix.clone.presentation.news.NewsAndHotViewModel
import com.netflix.clone.presentation.profile.MyNetflixScreen
import com.netflix.clone.presentation.profile.MyNetflixViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NetflixCloneNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Screen = Screen.Home,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        composable<Screen.Home> {
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                uiState = viewModel.uiState,
                navigateToMovieScreen = { id ->
                    navController.navigate(Screen.MovieScreen(id))
                },
            )
        }
        composable<Screen.Games> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(R.string.coming_soon))
            }
        }
        composable<Screen.NewsAndHot> {
            val viewModel = koinViewModel<NewsAndHotViewModel>()
            NewsAndHotScreen(uiState = viewModel.uiState)
        }
        composable<Screen.MyNetflix> {
            val viewModel = koinViewModel<MyNetflixViewModel>()
            MyNetflixScreen(uiState = viewModel.uiState)
        }
        composable<Screen.MovieScreen> {
            val viewModel = koinViewModel<MovieViewModel>()
            MovieScreen(uiState = viewModel.uiState)
        }
    }
}
