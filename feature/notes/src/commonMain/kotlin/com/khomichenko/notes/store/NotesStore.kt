package com.khomichenko.notes.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.database.entity.NoteEntity
import com.khomichenko.notes.store.NotesStore.Intent
import com.khomichenko.notes.store.NotesStore.State

internal interface NotesStore: Store<Intent, State, Nothing> {

    sealed interface Intent {
        data object DoSomething: Intent
    }

    sealed interface Result {
        data class DatabaseListFetched(val notes: List<NoteEntity>) : Result
    }

    sealed interface Action {
        class FetchDatabase(val notes: List<NoteEntity>) : Action
    }

    data class State(
        val listNotes: List<NoteEntity> = emptyList()
    )
}