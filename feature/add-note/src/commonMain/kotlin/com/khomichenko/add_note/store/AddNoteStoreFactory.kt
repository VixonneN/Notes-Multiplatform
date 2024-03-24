package com.khomichenko.add_note.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.add_note.store.AddNoteStore.*
import com.khomichenko.database.entity.NoteEntity
import com.khomichenko.database.repository.NotesDatabaseRepository
import kotlinx.coroutines.launch

internal class AddNoteStoreFactory(
    private val storeFactory: StoreFactory,
    private val databaseRepository: NotesDatabaseRepository
) {

    fun create(): AddNoteStore =
        object : AddNoteStore, Store<Intent, State, Nothing> by storeFactory.create(
            name = AddNoteStore::class.simpleName,
            initialState = State(),
            bootstrapper = SimpleBootstrapper(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Unit, State, Result, Nothing>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.ChangeNote -> dispatch(Result.NoteChanged(intent.newNote))
                is Intent.ChangeTitle -> dispatch(Result.TitleChanged(intent.newTitle))
                Intent.SaveNote -> scope.launch {
                    try {
                        saveNote(
                            title = getState().title,
                            note = getState().note
                        )

                    } catch (e: Throwable) {
                        e.printStackTrace()
                    }
                }
            }
        }

        private suspend fun saveNote(title: String, note: String) {
            println("saveNote in store")
            val noteEntity = NoteEntity(title = title, note = note, lastDateChanging = "")
            println(noteEntity.toString())
            databaseRepository.insertNote(noteEntity)
        }
    }

    private object ReducerImpl : Reducer<State, Result> {

        override fun State.reduce(msg: Result): State =
            when (msg) {
                is Result.TitleChanged -> copy(title = msg.newTitle)
                is Result.NoteChanged -> copy(note = msg.newNote)
            }

    }
}