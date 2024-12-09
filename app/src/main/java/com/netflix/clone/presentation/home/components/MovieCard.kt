package com.netflix.clone.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.netflix.clone.R
import com.netflix.clone.core.ext.scrim
import com.netflix.clone.presentation.components.PrimaryButtonSmall
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme

@Composable
fun MovieCard(
    posterPath: String?,
    modifier: Modifier = Modifier,
) {
    var scrim =
        listOf(
            Color.Transparent,
            Color.Transparent,
            MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
        )

    OutlinedCard(modifier = modifier.aspectRatio(2f / 3f)) {
        Box {
            AsyncImage(
                model =
                    ImageRequest
                        .Builder(LocalContext.current)
                        .data(posterPath)
                        .crossfade(true)
                        .build(),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds,
            )
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .scrim(scrim),
            )
            Row(
                modifier =
                    Modifier
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .align(Alignment.BottomCenter),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                PrimaryButtonSmall(
                    text = stringResource(R.string.play),
                    imageVector = Icons.Default.PlayArrow,
                    modifier = Modifier.weight(1f),
                )
                PrimaryButtonSmall(
                    text = stringResource(R.string.my_list),
                    imageVector = Icons.Default.Add,
                    modifier = Modifier.weight(1f),
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                    contentColor = ExtendedTheme.colors.neutralWhite,
                )
            }
        }
    }
}

@Preview
@Composable
private fun MovieCardPreview() {
    NetflixCloneTheme {
        MovieCard(posterPath = null)
    }
}
