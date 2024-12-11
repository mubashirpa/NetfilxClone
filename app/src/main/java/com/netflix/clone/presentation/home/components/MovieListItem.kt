package com.netflix.clone.presentation.home.components

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.netflix.clone.core.Constants
import com.netflix.clone.ui.theme.NetflixCloneTheme

@Composable
fun MovieListItem(
    onClick: () -> Unit,
    posterPath: String?,
    modifier: Modifier = Modifier,
    @FloatRange(from = 0.0, fromInclusive = false) ratio: Float = 2f / 3f,
) {
    Card(onClick = onClick, modifier = modifier) {
        AsyncImage(
            model =
                ImageRequest
                    .Builder(LocalContext.current)
                    .data("${Constants.TMDB_POSTER_PREFIX}$posterPath")
                    .crossfade(true)
                    .build(),
            contentDescription = null,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(ratio),
            contentScale = ContentScale.FillBounds,
        )
    }
}

@Preview
@Composable
private fun MovieListItemPreview() {
    NetflixCloneTheme {
        MovieListItem(onClick = {}, posterPath = null)
    }
}
