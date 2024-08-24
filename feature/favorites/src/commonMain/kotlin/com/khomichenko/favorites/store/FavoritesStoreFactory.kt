package com.khomichenko.favorites.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.database.repository.NotesDatabaseRepository
import com.khomichenko.favorites.store.FavoritesStore.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class FavoritesStoreFactory(
    private val storeFactory: StoreFactory,
    private val databaseRepository: NotesDatabaseRepository
) {

    fun create(): FavoritesStore =
        object : FavoritesStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = FavoritesStore::class.simpleName,
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
