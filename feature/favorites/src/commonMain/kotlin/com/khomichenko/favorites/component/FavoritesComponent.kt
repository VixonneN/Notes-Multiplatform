package com.khomichenko.favorites.component

import com.khomichenko.favorites.mapper.Note
import kotlinx.coroutines.flow.Flow

interface FavoritesComponent {

    data class Model(
        val listNotes: List<Note> = emptyList()
    )

    val state: Flow<Model>

    fun openBottomSheetComponent()
    fun openEditSlotComponent(id: Int)
}
