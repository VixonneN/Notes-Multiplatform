package com.khomichenko.notes.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.database.repository.NotesDatabaseRepository
import com.khomichenko.notes.store.NotesStore.Action
import com.khomichenko.notes.store.NotesStore.Intent
import com.khomichenko.notes.store.NotesStore.Result
import com.khomichenko.notes.store.NotesStore.State
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class NotesStoreFactory(
    private val storeFactory: StoreFactory,
    private val databaseRepository: NotesDatabaseRepository
) {

    fun create(): NotesStore =
        object : NotesStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = NotesStore::class.simpleName,
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            databaseRepository.getAllNotes()
                .onEach { notes -> dispatch(Action.FetchDatabase(notes)) }
                .launchIn(scope)
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Result, Nothing>() {
        override fun executeIntent(intent: Intent) {
            when (intent) {
                Intent.DoSomething -> doHust()
            }
        }

        private fun doHust() {
            println("doHUST")
        }

        override fun executeAction(action: Action) {
            when (action) {
                is Action.FetchDatabase -> dispatch(Result.DatabaseListFetched(notes = action.notes))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Result> {
        override fun State.reduce(msg: Result): State =
            when (msg) {
                is Result.DatabaseListFetched -> copy(listNotes = msg.notes)
            }

    }
}
