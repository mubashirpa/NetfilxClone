package com.netflix.clone.presentation.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Primary
val red = Color(0xFFD22F26)
val redLight1 = Color(0xFFDF7375)
val redDark1 = Color(0xFFB1060F)

// Neutral
val white = Color(0xFFFFFFFF)
val black = Color(0xFF000000)
val gray = Color(0xFF737373)
val grayDark1 = Color(0xFF323232)
val grayDark2 = Color(0xFF191919)
val grayDark3 = Color(0xFF010101)
val grayLight1 = Color(0xFFB2B2B2)
val grayLight2 = Color(0xFFCBCBCB)
val grayLight3 = Color(0xFFE6E6E6)

// System
val systemRed = Color(0xFFFE0202)
val systemBlue = Color(0xFF4B6AE4)

@Immutable
data class ExtendedColors(
    val primaryRed: Color,
    val primaryRedLight: Color,
    val primaryRedDark: Color,
    val neutralWhite: Color,
    val neutralBlack: Color,
    val neutralGray: Color,
    val neutralGrayDark1: Color,
    val neutralGrayDark2: Color,
    val neutralGrayDark3: Color,
    val neutralGrayLight1: Color,
    val neutralGrayLight2: Color,
    val neutralGrayLight3: Color,
    val systemRed: Color,
    val systemBlue: Color,
)

val LocalExtendedColors =
    staticCompositionLocalOf {
        ExtendedColors(
            primaryRed = Color.Unspecified,
            primaryRedLight = Color.Unspecified,
            primaryRedDark = Color.Unspecified,
            neutralWhite = Color.Unspecified,
            neutralBlack = Color.Unspecified,
            neutralGray = Color.Unspecified,
            neutralGrayDark1 = Color.Unspecified,
            neutralGrayDark2 = Color.Unspecified,
            neutralGrayDark3 = Color.Unspecified,
            neutralGrayLight1 = Color.Unspecified,
            neutralGrayLight2 = Color.Unspecified,
            neutralGrayLight3 = Color.Unspecified,
            systemRed = Color.Unspecified,
            systemBlue = Color.Unspecified,
        )
    }
