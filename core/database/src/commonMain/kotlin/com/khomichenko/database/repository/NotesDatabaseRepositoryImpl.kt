package com.khomichenko.database.repository

import app.cash.sqldelight.coroutines.asFlow
import com.khomichenko.database.db.notesCacheDatabase
import com.khomichenko.database.entity.NoteEntity
import com.khomichenko.database.mappers.toDBO
import com.khomichenko.database.mappers.toEntity
import com.khomichenko.database.utils.mapToList
import com.khomichenko.database.utils.mapToOne
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

internal class NotesDatabaseRepositoryImpl(
    database: notesCacheDatabase
) : NotesDatabaseRepository {

    private val databaseQuery = database.notesCacheDBOQueries
    override fun getAllNotes(): Flow<List<NoteEntity>> =
        databaseQuery.getAllNotes()
            .asFlow()
            .mapToList()
            .map { list -> list.map { noteDBO -> noteDBO.toEntity() } }
            .flowOn(Dispatchers.IO)

    override fun getNoteById(id: Int): Flow<NoteEntity> =
        databaseQuery.getNoteById(id)
            .asFlow()
            .mapToOne()
            .map { noteDBO -> noteDBO.toEntity() }

    override suspend fun insertNote(note: NoteEntity) {
        println(note.toString())
        note.toDBO().let {
            databaseQuery.insertNote(
                title = it.title,
                note = it.note,
                last_date_changing = it.last_date_changing
            )
        }
    }

    override suspend fun deleteNote(note: NoteEntity) {
        note.id.let {
            databaseQuery.deleteNote(note.id)
        }
    }
}
