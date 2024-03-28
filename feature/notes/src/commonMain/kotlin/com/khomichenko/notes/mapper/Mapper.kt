package com.khomichenko.notes.mapper

import com.khomichenko.database.entity.NoteEntity

fun NoteEntity.toNote() =
    Note(
        id = id,
        title = title,
        note = note,
        lastDateChanging = lastDateChanging
    )