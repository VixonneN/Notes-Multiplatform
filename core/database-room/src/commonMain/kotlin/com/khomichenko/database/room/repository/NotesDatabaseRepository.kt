package com.khomichenko.database.repository

import com.khomichenko.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesDatabaseRepository {
    fun getAllNotes(): Flow<List<NoteEntity>>
    fun getNoteById(id: Int): Flow<NoteEntity>

    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)

}