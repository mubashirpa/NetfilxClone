package com.netflix.clone.presentation.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.netflix.clone.R
import com.netflix.clone.presentation.news.components.NewAndHotListItem
import com.netflix.clone.presentation.theme.ExtendedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsAndHotScreen(
    uiState: NewsAndHotUiState,
    modifier: Modifier = Modifier,
) {
    val filters = stringArrayResource(R.array.news_and_hot_filters)
    val filterIcons =
        intArrayOf(
            R.drawable.ic_popcorn,
            R.drawable.ic_fire,
            R.drawable.ic_games,
            R.drawable.ic_top_10,
            R.drawable.ic_top_10,
        )
    val upcomingMovies = uiState.upcomingMovies.collectAsLazyPagingItems()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(R.string.title_news_and_hot_screen))
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Download,
                            contentDescription = null,
                        )
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = ExtendedTheme.colors.neutralBlack,
                        titleContentColor = ExtendedTheme.colors.neutralWhite,
                        actionIconContentColor = ExtendedTheme.colors.neutralWhite,
                    ),
            )
        },
        containerColor = ExtendedTheme.colors.neutralBlack,
        contentColor = ExtendedTheme.colors.neutralWhite,
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Row(
                modifier =
                    Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                filters.forEachIndexed { index, text ->
                    FilterChip(
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = text)
                        },
                        selected = index == 0,
                        leadingIcon = {
                            Image(
                                painter = painterResource(filterIcons[index]),
                                contentDescription = text,
                                modifier = Modifier.size(18.dp),
                            )
                        },
                        shape = MaterialTheme.shapes.large,
                        colors =
                            FilterChipDefaults.filterChipColors(
                                containerColor = ExtendedTheme.colors.neutralBlack,
                                labelColor = ExtendedTheme.colors.neutralWhite,
                                selectedContainerColor = ExtendedTheme.colors.neutralWhite,
                                selectedLabelColor = ExtendedTheme.colors.neutralBlack,
                            ),
                    )
                }
            }
            if (upcomingMovies.loadState.refresh is LoadState.NotLoading) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(start = 16.dp, end = 16.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    content = {
                        items(
                            count = upcomingMovies.itemCount,
                            key = upcomingMovies.itemKey(),
                            contentType = upcomingMovies.itemContentType(),
                        ) { index ->
                            upcomingMovies[index]?.let { item ->
                                NewAndHotListItem(
                                    onClick = { /*TODO*/ },
                                    title = item.title.orEmpty(),
                                    overview = item.overview.orEmpty(),
                                    posterPath = item.backdropPath,
                                )
                            }
                        }
                    },
                )
            }
        }
    }
}
