package com.netflix.clone.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.netflix.clone.presentation.games.GamesScreen
import com.netflix.clone.presentation.games.GamesViewModel
import com.netflix.clone.presentation.home.HomeScreen
import com.netflix.clone.presentation.home.HomeViewModel
import com.netflix.clone.presentation.movie.MovieScreen
import com.netflix.clone.presentation.movie.MovieViewModel
import com.netflix.clone.presentation.news.NewsAndHotScreen
import com.netflix.clone.presentation.news.NewsAndHotViewModel
import com.netflix.clone.presentation.profile.MyNetflixScreen
import com.netflix.clone.presentation.profile.MyNetflixViewModel
import com.netflix.clone.presentation.search.SearchScreen
import com.netflix.clone.presentation.search.SearchViewModel
import com.netflix.clone.presentation.tv.TvScreen
import com.netflix.clone.presentation.tv.TvViewModel
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
                navigateToTvScreen = { id ->
                    navController.navigate(Screen.TvScreen(id))
                },
                navigateToSearchScreen = {
                    navController.navigate(Screen.Search)
                },
            )
        }
        composable<Screen.Games> {
            val viewModel = koinViewModel<GamesViewModel>()
            GamesScreen(uiState = viewModel.uiState)
        }
        composable<Screen.NewsAndHot> {
            val viewModel = koinViewModel<NewsAndHotViewModel>()
            NewsAndHotScreen(uiState = viewModel.uiState)
        }
        composable<Screen.MyNetflix> {
            val viewModel = koinViewModel<MyNetflixViewModel>()
            MyNetflixScreen(uiState = viewModel.uiState)
        }
        composable<Screen.MovieScreen>(
            deepLinks =
                listOf(navDeepLink<Screen.MovieScreen>(basePath = "https://www.themoviedb.org/movie")),
        ) {
            val viewModel = koinViewModel<MovieViewModel>()
            MovieScreen(
                uiState = viewModel.uiState,
                onNavigateUp = {
                    navController.navigateUp()
                },
            )
        }
        composable<Screen.TvScreen>(
            deepLinks =
                listOf(navDeepLink<Screen.TvScreen>(basePath = "https://www.themoviedb.org/tv")),
        ) {
            val viewModel = koinViewModel<TvViewModel>()
            TvScreen(
                uiState = viewModel.uiState,
                onNavigateUp = {
                    navController.navigateUp()
                },
            )
        }
        composable<Screen.Search> {
            val viewModel = koinViewModel<SearchViewModel>()
            SearchScreen(
                uiState = viewModel.uiState,
                onNavigateUp = {
                    navController.navigateUp()
                },
            )
        }
    }
}
