package com.khomichenko.favorites.mapper

data class Note(
    val id: Long = 0,
    val title: String,
    val note: String,
    val lastDateChanging: String
)
