package com.khomichenko.database.mappers

import com.khomichenko.database.db.NotesCacheDBO
import com.khomichenko.database.entity.NoteEntity

fun NotesCacheDBO.toEntity() =
    NoteEntity(
        id = id,
        title = title,
        note = note,
        lastDateChanging = last_date_changing
    )

fun NoteEntity.toDBO() =
    NotesCacheDBO(
        id = id,
        title = title,
        note = note,
        last_date_changing = lastDateChanging
    )