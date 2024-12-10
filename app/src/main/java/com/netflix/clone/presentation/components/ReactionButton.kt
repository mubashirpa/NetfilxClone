package com.netflix.clone.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.netflix.clone.ui.theme.ExtendedTheme

@Composable
fun ReactionButton(
    onClick: () -> Unit,
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .sizeIn(minWidth = 96.dp, minHeight = 96.dp)
                .clip(CircleShape)
                .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = ExtendedTheme.colors.neutralWhite,
        )
        Text(
            text = label,
            modifier = Modifier.padding(top = 4.dp).widthIn(max = 88.dp),
            color = ExtendedTheme.colors.neutralGrayLight2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            minLines = 2,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
