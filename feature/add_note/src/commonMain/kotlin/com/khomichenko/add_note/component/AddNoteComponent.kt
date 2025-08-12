package com.khomichenko.add_note.component

import com.khomichenko.add_note.store.AddNoteStore
import kotlinx.coroutines.flow.StateFlow

interface AddNoteComponent {

    val state: StateFlow<AddNoteStore.State>

    fun changeTitle(title: String)
    fun changeNote(note: String)
    fun saveNote()
}