package com.khomichenko.notes.component

import com.khomichenko.notes.store.NotesStore
import kotlinx.coroutines.flow.StateFlow

interface NotesComponent {

    val state: StateFlow<NotesStore.State>

    fun openBottomSheetComponent()
}