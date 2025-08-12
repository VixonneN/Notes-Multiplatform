package com.khomichenko.database.room.repository

import com.khomichenko.database.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NotesDatabaseRepository {
    fun getAllNotes(): Flow<List<NoteEntity>>
    fun getNoteById(id: String): Flow<NoteEntity>

    suspend fun upsertNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)

}