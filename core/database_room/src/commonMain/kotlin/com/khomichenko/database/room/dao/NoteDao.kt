package com.khomichenko.database.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.khomichenko.database.room.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM note_entity")
    fun getAllNotes() : Flow<List<NoteEntity>>

    @Query("SELECT * FROM note_entity WHERE id = :id")
    fun selectNoteById(id: String) : Flow<NoteEntity>

    @Upsert
    suspend fun upsertNote(noteEntity: NoteEntity)

    @Delete
    suspend fun deleteNote(noteEntity: NoteEntity)
}