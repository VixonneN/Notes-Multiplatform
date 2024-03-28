package com.khomichenko.notes.component

import com.khomichenko.notes.mapper.Note
import kotlinx.coroutines.flow.Flow

interface NotesComponent {

    data class Model(
        val listNotes: List<Note> = emptyList()
    )

    val state: Flow<Model>

    fun openBottomSheetComponent()
}



