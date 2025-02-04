package com.khomichenko.database.room.repository

import com.khomichenko.database.room.dao.NoteDao
import com.khomichenko.database.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

internal class NotesDatabaseRepositoryImpl(
    private val dao: NoteDao
) : NotesDatabaseRepository {

    override fun getAllNotes(): Flow<List<NoteEntity>> =
        dao.getAllNotes()

    override fun getNoteById(id: Long): Flow<NoteEntity> =
        dao.selectNoteById(id)

    override suspend fun upsertNote(note: NoteEntity) {
        dao.upsertNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        dao.deleteNote(note)
    }
}
