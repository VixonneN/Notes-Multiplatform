package com.khomichenko.edit_note.component

import kotlinx.coroutines.flow.Flow

interface EditNoteComponent {

    data class Model(
        val id: String = "",
        val title: String = "",
        val note: String = ""
    )

    val state: Flow<Model>

    fun dismissComponent()
}