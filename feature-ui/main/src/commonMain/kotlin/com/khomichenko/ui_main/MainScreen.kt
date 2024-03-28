package com.khomichenko.ui_main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.khomichenko.edit_note.EditNoteScreen
import com.khomichenko.main.component.MainComponent
import com.khomichenko.ui_add_note.AddNoteScreen
import com.khomichenko.ui_note.ListNotesScreen

@Composable
fun MainScreen(component: MainComponent) {
    Scaffold(
        bottomBar = {

        }
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier = Modifier.padding(paddingValues)
        ) {
            when(val child = it.instance) {
                is MainComponent.ChildBottomNavigation.ListNotes -> ListNotesScreen(child.component)
                is MainComponent.ChildBottomNavigation.FavoritesNotes -> TODO()
                is MainComponent.ChildBottomNavigation.Profile -> TODO()
            }
        }
    }

    val slotChild by component.slot.subscribeAsState()
    slotChild.child?.instance?.also { slot ->
        when(slot) {
            is MainComponent.SlotChild.AddNote -> AddNoteScreen(slot.component)
            is MainComponent.SlotChild.Settings -> TODO()
            is MainComponent.SlotChild.ShowNote -> EditNoteScreen(slot.component)
        }
    }
}