package com.khomichenko.edit_note.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.database.room.entity.NoteEntity
import com.khomichenko.edit_note.store.EditNoteStore.Intent
import com.khomichenko.edit_note.store.EditNoteStore.State

interface EditNoteStore : Store<Intent, State, Nothing> {

    sealed interface Intent

    data class State(
        val id: Long = 0,
        val title: String = "",
        val note: String = "",
        val lastTimeChanged: String = ""
    )

    sealed interface Action {
        class FetchNoteById(val note: NoteEntity) : Action
    }

    sealed interface Result {
        data class NoteAdded(
            val id: Long,
            val title: String,
            val note: String,
            val lastTimeChanged: String
        ) : Result
    }

}