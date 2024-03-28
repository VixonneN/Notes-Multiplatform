package com.khomichenko.add_note.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.khomichenko.add_note.store.AddNoteStore
import com.khomichenko.add_note.store.AddNoteStore.*
import com.khomichenko.add_note.store.AddNoteStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class AddNoteComponentImpl(
    componentContext: ComponentContext,
    private val closeSlotComponent:() -> Unit
) : AddNoteComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        AddNoteStoreFactory(
            storeFactory = get(),
            databaseRepository = get()
        ).create()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<State>
        get() = store.stateFlow

    override fun changeTitle(title: String) {
        store.accept(Intent.ChangeTitle(title))
    }

    override fun changeNote(note: String) {
        store.accept(Intent.ChangeNote(note))
    }

    override fun saveNote() {
        println("saveNote")
        store.accept(Intent.SaveNote { closeSlotComponent() })
    }
}