package com.khomichenko.ui.theme

import androidx.compose.ui.graphics.Color

data class CustomPaletteTheme(
    val backgroundColor: Color,
    val contentColor: Color,
    val textColor: Color,
    val errorColor: Color
)

val LightCustomScheme = CustomPaletteTheme(
    backgroundColor = Color.White,
    contentColor = Color.LightGray,
    textColor = Color.Black,
    errorColor = Color.Red
)

val DarkCustomScheme = CustomPaletteTheme(
    backgroundColor = Color.DarkGray,
    contentColor = Color.White,
    textColor = Color.White,
    errorColor = Color.Red
)
