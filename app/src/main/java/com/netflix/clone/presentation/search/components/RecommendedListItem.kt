package com.netflix.clone.presentation.search.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.netflix.clone.R
import com.netflix.clone.core.Constants
import com.netflix.clone.ui.theme.NetflixCloneTheme

@Composable
fun RecommendedListItem(
    name: String,
    backdropPath: String?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ListItem(
        headlineContent = {
            Text(text = name)
        },
        modifier = modifier.clickable(onClick = onClick),
        leadingContent = {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data("${Constants.TMDB_POSTER_PREFIX}$backdropPath")
                        .crossfade(true)
                        .build(),
                contentDescription = null,
                modifier =
                    Modifier
                        .width(120.dp)
                        .aspectRatio(16f / 9f)
                        .clip(MaterialTheme.shapes.extraSmall),
                contentScale = ContentScale.FillBounds,
            )
        },
        trailingContent = {
            Image(
                painter = painterResource(R.drawable.baseline_play_circle_24),
                contentDescription = null,
                modifier =
                    Modifier.size(30.dp),
            )
        },
        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
    )
}

@Preview
@Composable
private fun RecommendedListItemPreview() {
    NetflixCloneTheme {
        RecommendedListItem(
            name = "Game of Thrones",
            backdropPath = null,
            onClick = {},
        )
    }
}
