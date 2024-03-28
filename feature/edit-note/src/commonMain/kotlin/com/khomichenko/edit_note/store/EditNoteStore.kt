package com.khomichenko.edit_note.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.database.entity.NoteEntity
import com.khomichenko.edit_note.store.EditNoteStore.*

interface EditNoteStore : Store<Intent, State, Nothing> {

    sealed interface Intent

    data class State(
        val id: Int = 0,
        val title: String = "",
        val note: String = "",
        val lastTimeChanged: String = ""
    )

    sealed interface Action {
        class FetchNoteById(val note: NoteEntity) : Action
    }

    sealed interface Result {
        data class NoteAdded(
            val id: Int,
            val title: String,
            val note: String,
            val lastTimeChanged: String
        ) : Result
    }

}