package com.netflix.clone.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.netflix.clone.R
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.Movie
import com.netflix.clone.domain.model.series.Series
import com.netflix.clone.presentation.home.components.MovieCard
import com.netflix.clone.presentation.home.components.MovieListItem
import com.netflix.clone.presentation.theme.ExtendedTheme
import com.netflix.clone.presentation.theme.NetflixCloneDynamicTheme
import com.netflix.clone.presentation.theme.NetflixCloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    navigateToMovieScreen: (id: Int) -> Unit,
    navigateToTvScreen: (id: Int) -> Unit,
    navigateToSearchScreen: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    val appBarColors =
        TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            scrolledContainerColor = Color.Black.copy(alpha = 0.7f),
            navigationIconContentColor = ExtendedTheme.colors.neutralWhite,
            actionIconContentColor = ExtendedTheme.colors.neutralWhite,
        )
    val colorTransitionFraction by
        remember(scrollBehavior) {
            // derivedStateOf to prevent redundant recompositions when the content scrolls.
            derivedStateOf {
                val overlappingFraction = scrollBehavior.state.overlappedFraction
                if (overlappingFraction > 0.01f) 1f else 0f
            }
        }
    val appBarContainerColor by
        animateColorAsState(
            targetValue =
                lerp(
                    appBarColors.containerColor,
                    appBarColors.scrolledContainerColor,
                    FastOutLinearInEasing.transform(colorTransitionFraction),
                ),
            animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
            label = "AppBarColorAnimation",
        )
    val scrollState = rememberScrollState()
    val filterVisible by remember { derivedStateOf { scrollState.lastScrolledBackward || scrollState.value < 200 } }
    val filters = stringArrayResource(R.array.home_filters)

    NetflixCloneDynamicTheme(
        "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjZ8ohCXR_9pNW208fEDguPU1-K3Uc5fAliCB_cX3CTo9CfYBhu2uCPQ1pTTQQpEYfrlYoGoj15j3LIhblnKhta4huY3QcDmZad4E3q-51rCjRTUNbpO_4WiVBilN97cnLSFQA7KZCzjtLJ3l6-XCfmPd5rKg_GnOGSGeAu6h7tb6v91QANtnijmbuQnCX3/s4096/MV5BMjEzN2ZjYjUtZTI3NC00MzMyLWJiNDAtMDBiZGEzNTBiY2RkXkEyXkFqcGc@._V1_.jpg",
    ) {
        val colorStops =
            arrayOf(
                0.0f to MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                0.2f to MaterialTheme.colorScheme.primary.copy(alpha = 0.80f),
                1f to MaterialTheme.colorScheme.primary.copy(alpha = 0.50f),
            )

        Box(
            modifier =
                modifier
                    .nestedScroll(scrollBehavior.nestedScrollConnection)
                    .background(Brush.verticalGradient(colorStops = colorStops)),
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(ExtendedTheme.colors.neutralBlack.copy(alpha = 0.5f)),
            )
            HomeScreenContent(
                uiState = uiState,
                modifier =
                    Modifier
                        .verticalScroll(scrollState)
                        .statusBarsPadding()
                        .padding(top = 96.dp),
                navigateToMovieScreen = navigateToMovieScreen,
                navigateToTvScreen = navigateToTvScreen,
            )
            Column {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        Image(
                            painter = painterResource(R.drawable.ic_netflix),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                        )
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Download,
                                contentDescription = null,
                            )
                        }
                        IconButton(onClick = navigateToSearchScreen) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                            )
                        }
                    },
                    colors = appBarColors,
                    scrollBehavior = scrollBehavior,
                )
                AnimatedVisibility(filterVisible) {
                    Row(
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .background(appBarContainerColor)
                                .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        filters.forEach {
                            FilterChip(
                                onClick = { /*TODO*/ },
                                label = {
                                    Text(text = it)
                                },
                                selected = false,
                                shape = MaterialTheme.shapes.large,
                            )
                        }
                        FilterChip(
                            onClick = { /*TODO*/ },
                            label = {
                                Text(text = stringResource(R.string.categories))
                            },
                            selected = false,
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                )
                            },
                            shape = MaterialTheme.shapes.large,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
    navigateToMovieScreen: (id: Int) -> Unit,
    navigateToTvScreen: (id: Int) -> Unit,
) {
    Column(modifier = modifier.padding(vertical = 12.dp)) {
        MovieCard(
            genres = listOf("Drama", "Thriller", "Crime"),
            posterPath = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjZ8ohCXR_9pNW208fEDguPU1-K3Uc5fAliCB_cX3CTo9CfYBhu2uCPQ1pTTQQpEYfrlYoGoj15j3LIhblnKhta4huY3QcDmZad4E3q-51rCjRTUNbpO_4WiVBilN97cnLSFQA7KZCzjtLJ3l6-XCfmPd5rKg_GnOGSGeAu6h7tb6v91QANtnijmbuQnCX3/s4096/MV5BMjEzN2ZjYjUtZTI3NC00MzMyLWJiNDAtMDBiZGEzNTBiY2RkXkEyXkFqcGc@._V1_.jpg",
            modifier = Modifier.padding(16.dp),
        )
        PopularMovies(
            items = uiState.popularMovies.collectAsLazyPagingItems(),
            onItemClick = navigateToMovieScreen,
        )
        TopRatedSeries(
            items = uiState.topRatedSeries.collectAsLazyPagingItems(),
            onItemClick = navigateToTvScreen,
        )
        PopularSeries(
            items = uiState.popularSeries.collectAsLazyPagingItems(),
            onItemClick = { /*TODO*/ },
        )
        Trending(
            uiState = uiState,
            onItemClick = { /*TODO*/ },
        )
    }
}

@Composable
private fun PopularMovies(
    items: LazyPagingItems<Movie>,
    onItemClick: (id: Int) -> Unit,
) {
    if (items.loadState.refresh !is LoadState.Loading && items.itemCount > 0) {
        Column {
            Text(
                text = stringResource(R.string.popular_movies),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                color = ExtendedTheme.colors.neutralWhite,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        count = items.itemCount,
                        key = items.itemKey(),
                        contentType = items.itemContentType(),
                    ) { index ->
                        items[index]?.let { item ->
                            MovieListItem(
                                onClick = { item.id?.let(onItemClick) },
                                posterPath = item.posterPath,
                                modifier = Modifier.width(100.dp),
                            )
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun TopRatedSeries(
    items: LazyPagingItems<Series>,
    onItemClick: (id: Int) -> Unit,
) {
    if (items.loadState.refresh !is LoadState.Loading && items.itemCount > 0) {
        Column {
            Text(
                text = stringResource(R.string.top_rated_tv_shows),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                color = ExtendedTheme.colors.neutralWhite,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        count = items.itemCount,
                        key = items.itemKey(),
                        contentType = items.itemContentType(),
                    ) { index ->
                        items[index]?.let { item ->
                            MovieListItem(
                                onClick = { item.id?.let(onItemClick) },
                                posterPath = item.posterPath,
                                modifier = Modifier.width(100.dp),
                            )
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun PopularSeries(
    items: LazyPagingItems<Series>,
    onItemClick: (id: Int) -> Unit,
) {
    if (items.loadState.refresh !is LoadState.Loading && items.itemCount > 0) {
        Column {
            Text(
                text = stringResource(R.string.only_on_netflix),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                color = ExtendedTheme.colors.neutralWhite,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        count = items.itemCount,
                        key = items.itemKey(),
                        contentType = items.itemContentType(),
                    ) { index ->
                        items[index]?.let { item ->
                            MovieListItem(
                                onClick = { item.id?.let(onItemClick) },
                                posterPath = item.posterPath,
                                modifier = Modifier.width(160.dp),
                                ratio = 9f / 16f,
                            )
                        }
                    }
                },
            )
        }
    }
}

@Composable
private fun Trending(
    uiState: HomeUiState,
    onItemClick: (id: Int) -> Unit,
) {
    if (uiState.trendingResource is Resource.Success) {
        val popularList = uiState.trendingResource.data.orEmpty()
        Column {
            Text(
                text = stringResource(R.string.today_s_top_picks_for_you),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 8.dp),
                color = ExtendedTheme.colors.neutralWhite,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                content = {
                    items(
                        items = popularList,
                        key = { it.id!! },
                    ) { result ->
                        MovieListItem(
                            onClick = { result.id?.let(onItemClick) },
                            posterPath = result.posterPath,
                            modifier = Modifier.width(100.dp),
                        )
                    }
                },
            )
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    NetflixCloneTheme {
        HomeScreen(
            uiState = HomeUiState(),
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(ExtendedTheme.colors.neutralBlack),
            navigateToMovieScreen = {},
            navigateToTvScreen = {},
            navigateToSearchScreen = {},
        )
    }
}
