package com.khomichenko.notes.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.khomichenko.notes.store.NotesStore
import com.khomichenko.notes.store.NotesStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
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

    @OptIn(ExperimentalCoroutinesApi::class)
    override val state: StateFlow<NotesStore.State>
        get() = store.stateFlow

    override fun openBottomSheetComponent() {
        openAddNoteSlot()
    }

}