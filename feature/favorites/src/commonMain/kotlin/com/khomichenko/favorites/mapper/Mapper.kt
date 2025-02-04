package com.khomichenko.favorites.mapper

import com.khomichenko.database.room.entity.NoteEntity


fun NoteEntity.toNote() =
    Note(
        id = id,
        title = title,
        note = note,
        lastDateChanging = lastDateChanging
    )