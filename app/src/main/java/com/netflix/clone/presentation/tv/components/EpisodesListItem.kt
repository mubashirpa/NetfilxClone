package com.netflix.clone.presentation.tv.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.netflix.clone.R
import com.netflix.clone.core.Constants
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme

@Composable
fun EpisodesListItem(
    name: String,
    duration: String,
    backdropPath: String?,
    overview: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(modifier = modifier.clickable(onClick = onClick)) {
        ListItem(
            headlineContent = {
                Text(text = name)
            },
            supportingContent = {
                Text(text = duration)
            },
            leadingContent = {
                Box {
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
                    Image(
                        painter = painterResource(R.drawable.baseline_play_circle_24),
                        contentDescription = null,
                        modifier =
                            Modifier
                                .size(40.dp)
                                .align(Alignment.Center),
                    )
                }
            },
            trailingContent = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Download, contentDescription = null)
                }
            },
            colors = ListItemDefaults.colors(containerColor = Color.Transparent),
        )
        if (overview.isNotEmpty()) {
            Text(
                text = overview,
                modifier = Modifier.padding(horizontal = 16.dp).padding(top = 4.dp, bottom = 12.dp),
                color = ExtendedTheme.colors.neutralGrayLight2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

@Preview
@Composable
private fun EpisodesListItemPreview() {
    NetflixCloneTheme {
        EpisodesListItem(
            name = "Episode 1",
            duration = "45 min",
            backdropPath = null,
            overview = "Overview",
            onClick = {},
        )
    }
}
