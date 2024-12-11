package com.netflix.clone.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.MicNone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.netflix.clone.R
import com.netflix.clone.domain.model.Game
import com.netflix.clone.presentation.games.components.GamesListItem
import com.netflix.clone.presentation.search.components.RecommendedListItem
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Column {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = onNavigateUp) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Download,
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
                SearchTextField()
            }
        },
        containerColor = ExtendedTheme.colors.neutralBlack,
        contentColor = ExtendedTheme.colors.neutralWhite,
    ) { innerPadding ->
        SearchScreenContent(
            uiState = uiState,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun SearchScreenContent(
    uiState: SearchUiState,
    modifier: Modifier = Modifier,
) {
    val recommended = uiState.trendingResource.data.orEmpty()

    LazyColumn(modifier = modifier) {
        item {
            Games(
                title = stringResource(R.string.recommended_mobile_games),
                gamesList = uiState.recommendedGames,
                onItemClick = { /*TODO*/ },
            )
            Text(
                text = stringResource(R.string.recommended_tv_shows_movies),
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 24.dp, bottom = 8.dp),
                color = ExtendedTheme.colors.neutralWhite,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        items(items = recommended, key = { it.id!! }) {
            val name = if (it.title.isNullOrEmpty()) it.name else it.title
            RecommendedListItem(
                name = name.orEmpty(),
                backdropPath = it.backdropPath,
                onClick = { /*TODO*/ },
            )
        }
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
            style = MaterialTheme.typography.titleLarge,
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

@Composable
private fun SearchTextField() {
    var value by rememberSaveable { mutableStateOf("") }
    val textColor = ExtendedTheme.colors.neutralWhite
    val mergedTextStyle = MaterialTheme.typography.bodyLarge.merge(TextStyle(color = textColor))

    BasicTextField(
        value = value,
        onValueChange = { value = it },
        modifier =
            Modifier
                .height(56.dp)
                .fillMaxWidth(),
        textStyle = mergedTextStyle,
        maxLines = 1,
        cursorBrush = SolidColor(textColor),
        decorationBox = { innerTextField ->
            Row(
                modifier =
                    Modifier
                        .background(ExtendedTheme.colors.neutralGrayDark1)
                        .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = ExtendedTheme.colors.neutralGrayLight2,
                )
                Box(modifier = Modifier.weight(1f)) {
                    if (value.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_games_shows_movies),
                            color = ExtendedTheme.colors.neutralGrayLight1,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    innerTextField()
                }
                Icon(
                    imageVector = Icons.Default.MicNone,
                    contentDescription = null,
                    tint = ExtendedTheme.colors.neutralGrayLight2,
                )
            }
        },
    )
}

@Preview
@Composable
private fun SearchScreenPreview() {
    NetflixCloneTheme {
        SearchScreen(
            uiState = SearchUiState(),
            onNavigateUp = {},
        )
    }
}
