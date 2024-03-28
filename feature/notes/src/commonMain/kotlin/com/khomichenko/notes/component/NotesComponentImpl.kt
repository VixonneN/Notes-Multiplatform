package com.khomichenko.notes.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.khomichenko.notes.component.NotesComponent.*
import com.khomichenko.notes.mapper.toNote
import com.khomichenko.notes.store.NotesStore
import com.khomichenko.notes.store.NotesStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class NotesComponentImpl(
    componentContext: ComponentContext,
    private val openAddNoteSlot:() -> Unit
) : NotesComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        NotesStoreFactory(
            storeFactory = get(),
            databaseRepository = get()
        ).create()
    }

    init {
        store.accept(NotesStore.Intent.DoSomething)
    }

    override val state: Flow<Model>
        get() = store.states.map {
            Model(
                listNotes = it.listNotes.map { list -> list.toNote() }
            )
        }

    override fun openBottomSheetComponent() {
        openAddNoteSlot()
    }

}