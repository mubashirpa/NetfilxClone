package com.netflix.clone.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import com.netflix.clone.core.utils.DynamicThemePrimaryColorsFromImage
import com.netflix.clone.core.utils.contrastAgainst
import com.netflix.clone.core.utils.rememberDominantColorState

val DefaultExtendedColors =
    ExtendedColors(
        primaryRed = red,
        primaryRedLight = redLight1,
        primaryRedDark = redDark1,
        neutralWhite = white,
        neutralBlack = black,
        neutralGray = gray,
        neutralGrayDark1 = grayDark1,
        neutralGrayDark2 = grayDark2,
        neutralGrayDark3 = grayDark3,
        neutralGrayLight1 = grayLight1,
        neutralGrayLight2 = grayLight2,
        neutralGrayLight3 = grayLight3,
        systemRed = systemRed,
        systemBlue = systemBlue,
    )

@Composable
fun NetflixCloneTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalExtendedColors provides DefaultExtendedColors) {
        MaterialTheme(
            colorScheme = darkColorScheme(),
            typography = Typography,
            content = content,
        )
    }
}

/**
 * This is the minimum amount of calculated contrast for a color to be used on top of the
 * surface color. These values are defined within the WCAG AA guidelines, and we use a value of
 * 3:1 which is the minimum for user-interface components.
 */
const val MinContrastOfPrimaryVsSurface = 3f

/**
 * Theme that updates the colors dynamically depending on the podcast image URL
 */
@Composable
fun NetflixCloneDynamicTheme(
    imageUrl: String,
    content: @Composable () -> Unit,
) {
    val surfaceColor = MaterialTheme.colorScheme.surface
    val dominantColorState =
        rememberDominantColorState { color ->
            // We want a color which has sufficient contrast against the surface color
            color.contrastAgainst(surfaceColor) >= MinContrastOfPrimaryVsSurface
        }
    DynamicThemePrimaryColorsFromImage(dominantColorState) {
        // Update the dominantColorState with colors coming from the podcast image URL
        LaunchedEffect(imageUrl) {
            if (imageUrl.isNotEmpty()) {
                dominantColorState.updateColorsFromImageUrl(imageUrl)
            } else {
                dominantColorState.reset()
            }
        }
        content()
    }
}

object ExtendedTheme {
    val colors: ExtendedColors
        @Composable
        get() = LocalExtendedColors.current
}
