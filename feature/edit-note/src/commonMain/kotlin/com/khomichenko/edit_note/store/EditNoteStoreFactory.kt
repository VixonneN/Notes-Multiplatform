package com.khomichenko.edit_note.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.database.room.entity.NoteEntity
import com.khomichenko.database.room.repository.NotesDatabaseRepository
import com.khomichenko.edit_note.store.EditNoteStore.Action
import com.khomichenko.edit_note.store.EditNoteStore.Intent
import com.khomichenko.edit_note.store.EditNoteStore.Result
import com.khomichenko.edit_note.store.EditNoteStore.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class EditNoteStoreFactory(
    private val storeFactory: StoreFactory,
    private val databaseRepository: NotesDatabaseRepository,
    private val idNote: Long
) {

    fun create() : EditNoteStore =
        object : EditNoteStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = EditNoteStore::class.simpleName,
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            databaseRepository.getNoteById(idNote)
                .onEach { notes -> dispatch(Action.FetchNoteById(notes)) }
                .launchIn(scope)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Result, Nothing>() {
        override fun executeAction(action: Action) {
            when(action) {
                is Action.FetchNoteById -> mapNoteDbToNote(action.note)
            }
        }

        private fun mapNoteDbToNote(noteEntity: NoteEntity) {
            val noteId = noteEntity.id
            val noteTitle = noteEntity.title
            val noteText = noteEntity.note
            val noteLastDateChanging = noteEntity.lastDateChanging

            dispatch(Result.NoteAdded(noteId, noteTitle, noteText, noteLastDateChanging))
        }

        override fun executeIntent(intent: Intent) {
            when(intent) {

                else -> {}
            }
        }
    }

    private object ReducerImpl: Reducer<State, Result> {
        override fun State.reduce(msg: Result): State =
            when(msg) {
                is Result.NoteAdded -> copy(
                    id = msg.id,
                    title = msg.title,
                    note = msg.note,
                    lastTimeChanged = msg.lastTimeChanged
                )
            }
    }
}