package com.khomichenko.notes.mapper

data class Note(
    val id: Int = 0,
    val title: String,
    val note: String,
    val lastDateChanging: String
)
