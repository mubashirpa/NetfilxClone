package com.netflix.clone.presentation.news.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.netflix.clone.R
import com.netflix.clone.core.Constants
import com.netflix.clone.presentation.components.PrimaryButtonSmall
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme

@Composable
fun NewAndHotListItem(
    onClick: () -> Unit,
    title: String,
    overview: String,
    posterPath: String?,
    modifier: Modifier = Modifier,
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = ExtendedTheme.colors.neutralBlack),
    ) {
        Box {
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
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = title,
                color = ExtendedTheme.colors.neutralWhite,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                text = overview,
                modifier = Modifier.padding(top = 8.dp),
                color = ExtendedTheme.colors.neutralGrayLight1,
                style = MaterialTheme.typography.bodyMedium,
            )
            PrimaryButtonSmall(
                text = stringResource(R.string.remind_me),
                imageVector = Icons.Outlined.Notifications,
                modifier = Modifier.padding(top = 16.dp),
            )
        }
    }
}

@Preview
@Composable
private fun NewAndHotListItemPreview() {
    NetflixCloneTheme {
        NewAndHotListItem(
            onClick = {},
            title = "Elevation",
            overview =
                "A single father and two women venture from the safety of their homes to " +
                    "face monstrous creatures to save the life of a young boy.",
            posterPath = null,
        )
    }
}
