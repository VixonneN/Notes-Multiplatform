package com.khomichenko.edit_note.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.khomichenko.edit_note.store.EditNoteStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class EditNoteComponentImpl(
    componentContext: ComponentContext,
    private val idNote: Int,
    private val closeSlotComponent:() -> Unit
) : EditNoteComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        EditNoteStoreFactory(
            storeFactory = get(),
            databaseRepository = get(),
            idNote = idNote
        ).create()
    }

    override val state: Flow<EditNoteComponent.Model>
        get() = store.states.map {
            EditNoteComponent.Model(
                id = it.id,
                title = it.title,
                note = it.note
            )
        }

    override fun dismissComponent() {
        closeSlotComponent()
    }


}