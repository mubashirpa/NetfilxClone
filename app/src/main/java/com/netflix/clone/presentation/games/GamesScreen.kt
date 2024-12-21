package com.netflix.clone.presentation.games

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.netflix.clone.R
import com.netflix.clone.domain.model.Game
import com.netflix.clone.presentation.games.components.GamesListItem
import com.netflix.clone.presentation.theme.ExtendedTheme
import com.netflix.clone.presentation.theme.NetflixCloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamesScreen(
    uiState: GamesUiState,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Box(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection)) {
        GamesContent(
            uiState = uiState,
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .statusBarsPadding()
                    .padding(top = 64.dp)
                    .padding(bottom = 12.dp),
        )
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.title_games_screen))
            },
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
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
private fun GamesContent(
    uiState: GamesUiState,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedCard(
            modifier =
                Modifier
                    .padding(horizontal = 16.dp)
                    .aspectRatio(4f / 5f),
        ) {
            AsyncImage(
                model = "file:///android_asset/images/games_banner.jpg",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
        }
        Games(
            title = stringResource(R.string.popular_games),
            gamesList = uiState.popularGames,
            onItemClick = { /*TODO*/ },
        )
        Games(
            title = stringResource(R.string.mobile_games),
            gamesList = uiState.mobileGames,
            onItemClick = { /*TODO*/ },
        )
    }
}

@Composable
private fun Games(
    title: String,
    gamesList: List<Game>,
    modifier: Modifier = Modifier,
    onItemClick: (id: Int) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            text = title,
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
                    items = gamesList,
                    key = { it.id },
                ) { game ->
                    GamesListItem(
                        onClick = { onItemClick(game.id) },
                        name = game.name,
                        type = game.type,
                        image = game.image,
                    )
                }
            },
        )
    }
}

@Preview
@Composable
private fun GamesScreenPreview() {
    NetflixCloneTheme {
        GamesScreen(
            uiState = GamesUiState(),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
