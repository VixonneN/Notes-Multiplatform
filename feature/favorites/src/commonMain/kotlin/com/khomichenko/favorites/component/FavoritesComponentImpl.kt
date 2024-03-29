package com.khomichenko.favorites.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.khomichenko.favorites.component.FavoritesComponent.*
import com.khomichenko.favorites.mapper.toNote
import com.khomichenko.favorites.store.FavoritesStore
import com.khomichenko.favorites.store.FavoritesStoreFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class FavoritesComponentImpl(
    componentContext: ComponentContext,
    private val openAddNoteSlot:() -> Unit,
    private val openEditNoteSlot:(id: Int) -> Unit
) : FavoritesComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        FavoritesStoreFactory(
            storeFactory = get(),
            databaseRepository = get()
        ).create()
    }

    init {
        store.accept(FavoritesStore.Intent.DoSomething)
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

    override fun openEditSlotComponent(id: Int) {
        openEditNoteSlot(id)
    }
}
