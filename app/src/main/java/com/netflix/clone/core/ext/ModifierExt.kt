package com.netflix.clone.core.ext

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

/**
 * Creates a vertical gradient with the given colors evenly dispersed within the gradient.
 *
 * @param colors colors Colors to be rendered as part of the gradient
 */
fun Modifier.scrim(colors: List<Color>): Modifier =
    drawWithContent {
        drawContent()
        drawRect(Brush.verticalGradient(colors))
    }
