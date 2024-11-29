package com.khomichenko.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

internal val LocalThemeIsDark = compositionLocalOf { mutableStateOf(true) }

internal val LocalColors =
    compositionLocalOf<CustomPaletteTheme> { error("Colors composition error") }

@Composable
fun CustomNotesTheme(
    content: @Composable () -> Unit
) {
    val systemIsDark = isSystemInDarkTheme()
    val isDarkState = remember { mutableStateOf(systemIsDark) }
    CompositionLocalProvider(LocalThemeIsDark provides isDarkState) {
        val isDark by isDarkState

        SystemAppearance(isDark)

        NotesTheme(
            colorScheme = if (isDark) DarkCustomScheme else LightCustomScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = {
                Surface(content = content)
            }
        )
    }
}

@Composable
fun NotesTheme(
    colorScheme: CustomPaletteTheme = NotesTheme.colors,
    shapes: Shapes = NotesTheme.shapes,
    typography: Typography = NotesTheme.typography,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalColors provides colorScheme,
        LocalShapes provides shapes,
        LocalTypography provides typography
    ) {
        ProvideTextStyle(value = typography.bodyLarge, content)
    }
}

object NotesTheme {

    val colors: CustomPaletteTheme
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current

    val shapes: Shapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

@Composable
internal expect fun SystemAppearance(isDark: Boolean)
