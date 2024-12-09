package com.netflix.clone.core.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class UiText {
    companion object {
        private const val EMPTY = ""
    }

    class StringResource(
        @StringRes val id: Int,
        vararg val args: Any,
    ) : UiText()

    data class DynamicString(
        val value: String,
    ) : UiText()

    data object Empty : UiText()

    @Composable
    fun asString(): String =
        when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id = id, formatArgs = args)
            Empty -> EMPTY
        }

    fun asString(context: Context): String =
        when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
            Empty -> EMPTY
        }
}
