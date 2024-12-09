package com.netflix.clone.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.netflix.clone.ui.theme.ExtendedTheme
import com.netflix.clone.ui.theme.NetflixCloneTheme

@Composable
fun PrimaryButtonLarge(
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: Color = ExtendedTheme.colors.neutralWhite,
    contentColor: Color = ExtendedTheme.colors.neutralBlack,
) {
    Button(
        onClick = {},
        modifier = modifier.fillMaxWidth().height(36.dp),
        shape = RoundedCornerShape(4.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        contentPadding =
            PaddingValues(
                horizontal = 8.dp,
                vertical = 6.dp,
            ),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = text)
    }
}

@Composable
fun PrimaryButtonSmall(
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: Color = ExtendedTheme.colors.neutralWhite,
    contentColor: Color = ExtendedTheme.colors.neutralBlack,
) {
    Button(
        onClick = {},
        modifier = modifier.height(36.dp),
        shape = RoundedCornerShape(4.dp),
        colors =
            ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
        contentPadding =
            PaddingValues(
                horizontal = 8.dp,
                vertical = 6.dp,
            ),
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(ButtonDefaults.IconSize),
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(text = text)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    containerColor: Color = ExtendedTheme.colors.neutralGrayDark2,
    contentColor: Color = ExtendedTheme.colors.neutralWhite,
) {
    PrimaryButtonLarge(
        text = text,
        imageVector = imageVector,
        modifier = modifier,
        containerColor = containerColor,
        contentColor = contentColor,
    )
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    NetflixCloneTheme {
        Column {
            PrimaryButtonLarge(text = "Play", imageVector = Icons.Filled.PlayArrow)
            Spacer(modifier = Modifier.height(16.dp))
            SecondaryButton(text = "Download", imageVector = Icons.Filled.Download)
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryButtonSmall(text = "Play", imageVector = Icons.Filled.PlayArrow)
        }
    }
}
