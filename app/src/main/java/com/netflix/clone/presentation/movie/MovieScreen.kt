package com.netflix.clone.presentation.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material.icons.outlined.Hd
import androidx.compose.material.icons.outlined.Subtitles
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.netflix.clone.R
import com.netflix.clone.core.Constants
import com.netflix.clone.core.utils.Resource
import com.netflix.clone.domain.model.movie.MovieResultModel
import com.netflix.clone.domain.model.movie.details.MovieDetails
import com.netflix.clone.presentation.components.PrimaryButtonLarge
import com.netflix.clone.presentation.components.ReactionButton
import com.netflix.clone.presentation.home.components.MovieListItem
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme
import kotlin.collections.orEmpty
import kotlin.collections.take

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieScreen(
    uiState: MovieUiState,
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
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
                        navigationIconContentColor = ExtendedTheme.colors.neutralWhite,
                        actionIconContentColor = ExtendedTheme.colors.neutralWhite,
                    ),
            )
        },
        containerColor = ExtendedTheme.colors.neutralBlack,
        contentColor = ExtendedTheme.colors.neutralWhite,
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            when (uiState.movieDetailsResource) {
                is Resource.Empty -> {}

                is Resource.Error -> {}

                is Resource.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        CircularProgressIndicator(color = ExtendedTheme.colors.systemRed)
                    }
                }

                is Resource.Success -> {
                    val movieDetails = uiState.movieDetailsResource.data

                    if (movieDetails != null) {
                        MovieContent(
                            movieDetails = movieDetails,
                            modifier = Modifier.verticalScroll(rememberScrollState()),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MovieContent(
    movieDetails: MovieDetails,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Box {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data("${Constants.TMDB_POSTER_PREFIX}${movieDetails.backdropPath}")
                        .crossfade(true)
                        .build(),
                contentDescription = null,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f),
                contentScale = ContentScale.FillBounds,
            )
            Image(
                painter = painterResource(R.drawable.baseline_play_circle_24),
                contentDescription = null,
                modifier =
                    Modifier
                        .size(48.dp)
                        .align(Alignment.Center),
            )
        }
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
        ) {
            TitleAndButtons(
                title = movieDetails.title.orEmpty(),
                releaseDate = movieDetails.releaseDate.orEmpty().take(4),
                runtime = movieDetails.runtime ?: 0,
                overview = movieDetails.overview.orEmpty(),
                casts =
                    movieDetails
                        .credits
                        ?.cast
                        ?.joinToString(", ") { it.name.orEmpty() }
                        .orEmpty(),
                director =
                    movieDetails
                        .credits
                        ?.crew
                        ?.firstOrNull { it.job == "Director" }
                        ?.name
                        .orEmpty(),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Reactions(modifier = Modifier.padding(vertical = 12.dp))
            Extra(movieDetails = movieDetails)
        }
    }
}

@Composable
private fun TitleAndButtons(
    title: String,
    releaseDate: String,
    runtime: Int,
    overview: String,
    casts: String,
    director: String,
    modifier: Modifier = Modifier,
) {
    val runtime =
        runtime.let {
            if (it > 0) {
                val hour = it / 60
                val minute = it % 60
                "${hour}h ${minute}m"
            } else {
                ""
            }
        }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            text = title,
            color = ExtendedTheme.colors.neutralWhite,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.headlineMedium,
        )
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Text(
                text = releaseDate,
                color = ExtendedTheme.colors.neutralGrayLight2,
            )
            Text(
                text = runtime,
                color = ExtendedTheme.colors.neutralGrayLight2,
            )
            Icon(
                imageVector = Icons.Outlined.Hd,
                contentDescription = null,
                tint = ExtendedTheme.colors.neutralGrayLight2,
            )
            Icon(
                imageVector = Icons.Outlined.Subtitles,
                contentDescription = null,
                tint = ExtendedTheme.colors.neutralGrayLight2,
            )
        }
        PrimaryButtonLarge(
            text = stringResource(R.string.play),
            imageVector = Icons.Default.PlayArrow,
            modifier = Modifier.height(40.dp),
        )
        PrimaryButtonLarge(
            text = stringResource(R.string.download),
            imageVector = Icons.Default.Download,
            modifier = Modifier.height(40.dp),
            containerColor = ExtendedTheme.colors.neutralGrayDark1,
            contentColor = ExtendedTheme.colors.neutralWhite,
        )
        Text(
            text = overview,
            color = ExtendedTheme.colors.neutralGrayLight3,
            overflow = TextOverflow.Ellipsis,
            maxLines = 3,
            style = MaterialTheme.typography.bodyMedium,
        )
        Column {
            Text(
                text = stringResource(R.string.starring, casts),
                color = ExtendedTheme.colors.neutralGrayLight2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
            )
            Text(
                text = stringResource(R.string.director, director),
                modifier = Modifier.padding(top = 4.dp),
                color = ExtendedTheme.colors.neutralGrayLight2,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Composable
private fun Reactions(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        ReactionButton(
            onClick = {},
            icon = Icons.Default.Add,
            label = stringResource(R.string.my_list),
        )
        ReactionButton(
            onClick = {},
            icon = Icons.Default.ThumbUpOffAlt,
            label = stringResource(R.string.rate),
        )
        ReactionButton(
            onClick = {},
            icon = Icons.Default.Share,
            label = stringResource(R.string.share),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Extra(
    movieDetails: MovieDetails,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        HorizontalDivider()
        PrimaryScrollableTabRow(
            selectedTabIndex = 0,
            containerColor = Color.Transparent,
            contentColor = ExtendedTheme.colors.neutralWhite,
            edgePadding = 0.dp,
            indicator = {
                FancyIndicator(
                    modifier =
                        Modifier.tabIndicatorOffset(
                            selectedTabIndex = 0,
                            matchContentSize = true,
                        ),
                )
            },
            divider = {},
        ) {
            Tab(
                selected = true,
                onClick = { /*TODO*/ },
                text = {
                    Text(
                        text = stringResource(R.string.more_like_this),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                selectedContentColor = ExtendedTheme.colors.neutralGrayLight2,
            )
        }
        Collection(collection = movieDetails.recommendations.orEmpty().take(9))
    }
}

@Composable
private fun FancyIndicator(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(3.dp)
                    .background(ExtendedTheme.colors.systemRed)
                    .align(Alignment.TopCenter),
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Collection(collection: List<MovieResultModel>) {
    FlowRow(
        modifier =
            Modifier
                .padding(horizontal = 12.dp)
                .padding(top = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        maxItemsInEachRow = 3,
    ) {
        val itemModifier =
            Modifier
                .width(100.dp)
                .weight(1f)

        collection.forEach { item ->
            MovieListItem(
                onClick = { /*TODO*/ },
                posterPath = item.posterPath,
                modifier = itemModifier,
            )
        }
    }
}

@Preview
@Composable
private fun ContentScreenPreview() {
    NetflixCloneTheme {
        MovieScreen(
            uiState = MovieUiState(movieDetailsResource = Resource.Success(MovieDetails())),
            modifier = Modifier.fillMaxSize(),
            onNavigateUp = {},
        )
    }
}
