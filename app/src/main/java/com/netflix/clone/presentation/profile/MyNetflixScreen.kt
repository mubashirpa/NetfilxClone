package com.netflix.clone.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.netflix.clone.R
import com.netflix.clone.domain.model.list.ListResultModel
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.presentation.home.components.MovieListItem
import com.netflix.clone.presentation.theme.ExtendedTheme
import com.netflix.clone.presentation.theme.NetflixCloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyNetflixScreen(
    uiState: MyNetflixUiState,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Box(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) {
        MyNetflixScreenContent(
            uiState = uiState,
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .statusBarsPadding()
                    .padding(top = 64.dp),
        )
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.title_my_netflix_screen))
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                    )
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                    )
                }
            },
            colors =
                TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Black.copy(alpha = 0.7f),
                    titleContentColor = ExtendedTheme.colors.neutralWhite,
                    actionIconContentColor = ExtendedTheme.colors.neutralWhite,
                ),
            scrollBehavior = scrollBehavior,
        )
    }
}

@Composable
private fun MyNetflixScreenContent(
    uiState: MyNetflixUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(vertical = 12.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_profile_placeholder),
            contentDescription = null,
            modifier =
                Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally),
        )
        Text(
            text = "John Doe",
            modifier =
                Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
            color = ExtendedTheme.colors.neutralWhite,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MyNetflixListItem(
            title = stringResource(R.string.notifications),
            icon = Icons.Default.Notifications,
            color = ExtendedTheme.colors.systemRed,
            modifier = Modifier.clickable(onClick = { /*TODO*/ }),
        )
        MyNetflixListItem(
            title = stringResource(R.string.downloads),
            icon = Icons.Default.Download,
            color = ExtendedTheme.colors.systemBlue,
            modifier = Modifier.clickable(onClick = { /*TODO*/ }),
        )
        MyList(
            onItemClick = { /*TODO*/ },
            items = uiState.myList.collectAsLazyPagingItems(),
            modifier = Modifier.padding(top = 8.dp),
        )
        NowPlayingMovies(
            onItemClick = { /*TODO*/ },
            items = uiState.nowPlayingMovies.collectAsLazyPagingItems(),
            modifier = Modifier.padding(top = 12.dp),
        )
    }
}

@Composable
private fun MyNetflixListItem(
    title: String,
    icon: ImageVector,
    color: Color,
    modifier: Modifier = Modifier,
) {
    ListItem(
        headlineContent = {
            Text(text = title, fontSize = 18.sp)
        },
        modifier = modifier,
        leadingContent = {
            Box(
                modifier =
                    Modifier
                        .size(48.dp)
                        .background(
                            color = color,
                            shape = CircleShape,
                        ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(imageVector = icon, contentDescription = null)
            }
        },
        trailingContent = {
            Icon(imageVector = Icons.Default.ChevronRight, contentDescription = null)
        },
        colors =
            ListItemDefaults.colors(
                containerColor = Color.Transparent,
                headlineColor = ExtendedTheme.colors.neutralWhite,
                trailingIconColor = ExtendedTheme.colors.neutralWhite,
            ),
    )
}

@Composable
private fun MyList(
    onItemClick: (id: Int) -> Unit,
    items: LazyPagingItems<ListResultModel>,
    modifier: Modifier = Modifier,
) {
    if (items.loadState.refresh is LoadState.NotLoading && items.itemCount > 0) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(R.string.my_list),
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
private fun NowPlayingMovies(
    onItemClick: (id: Int) -> Unit,
    items: LazyPagingItems<MovieResultModel>,
    modifier: Modifier = Modifier,
) {
    if (items.loadState.refresh is LoadState.NotLoading && items.itemCount > 0) {
        Column(modifier = modifier) {
            Text(
                text = stringResource(R.string.trailers_you_have_watched),
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

@Preview
@Composable
private fun MyNetflixScreenPreview() {
    NetflixCloneTheme {
        MyNetflixScreen(uiState = MyNetflixUiState(), modifier = Modifier.fillMaxSize())
    }
}
