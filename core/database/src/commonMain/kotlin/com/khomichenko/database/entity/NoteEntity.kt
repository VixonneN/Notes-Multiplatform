package com.khomichenko.database.entity

data class NoteEntity(
    val id: Int = 0,
    val title: String,
    val note: String,
    val lastDateChanging: String
)
