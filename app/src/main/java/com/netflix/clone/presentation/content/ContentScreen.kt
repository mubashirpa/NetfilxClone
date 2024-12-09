package com.netflix.clone.presentation.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.netflix.clone.presentation.components.PrimaryButtonLarge
import com.netflix.clone.ui.theme.ExtendedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier,
        containerColor = ExtendedTheme.colors.neutralBlack,
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Cast,
                            contentDescription = null,
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.Transparent,
                        navigationIconContentColor = Color.White,
                        actionIconContentColor = Color.White,
                    ),
            )
            AsyncImage(
                model = "",
                contentDescription = null,
                modifier =
                    Modifier
                        .aspectRatio(16f / 9f)
                        .background(Color.Gray),
            )
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                PrimaryButtonLarge(
                    text = "Play",
                    imageVector = Icons.Default.PlayArrow,
                )
                PrimaryButtonLarge(
                    text = "Download",
                    imageVector = Icons.Default.Download,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContentScreenPreview() {
    ContentScreen(modifier = Modifier.fillMaxSize())
}
