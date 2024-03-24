package com.khomichenko.add_note.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.database.entity.NoteEntity
import com.khomichenko.add_note.store.AddNoteStore.*

interface AddNoteStore: Store<Intent, State, Nothing> {

    sealed interface Intent {
        class ChangeTitle(val newTitle:String) : Intent
        class ChangeNote(val newNote:String) : Intent
        data object SaveNote : Intent
    }

    sealed interface Result {
        class TitleChanged(val newTitle: String): Result
        class NoteChanged(val newNote: String): Result
    }

    data class State(
        val title: String = "",
        val note: String = ""
    )
}