package com.khomichenko.ui_main

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.khomichenko.edit_note.EditNoteScreen
import com.khomichenko.favorites.FavoritesScreen
import com.khomichenko.main.component.MainComponent
import com.khomichenko.settings.SettingsScreen
import com.khomichenko.ui_add_note.AddNoteScreen
import com.khomichenko.ui_note.ListNotesScreen

@Composable
fun MainScreen(component: MainComponent) {
    Scaffold(
        bottomBar = {
            NotesBottomNavigation(component)
        },
        topBar = {
            MainTopBar(component = component)
        }
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val child = it.instance) {
                is MainComponent.ChildBottomNavigation.ListNotes -> ListNotesScreen(child.component)
                is MainComponent.ChildBottomNavigation.FavoritesNotes -> FavoritesScreen(child.component)
                is MainComponent.ChildBottomNavigation.Profile -> TODO()
            }
        }
    }

    val slotChild by component.slot.subscribeAsState()
    slotChild.child?.instance?.also { slot ->
        when (slot) {
            is MainComponent.SlotChild.AddNote -> AddNoteScreen(slot.component)
            is MainComponent.SlotChild.Settings -> SettingsScreen(slot.component)
            is MainComponent.SlotChild.ShowNote -> EditNoteScreen(slot.component)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainTopBar(component: MainComponent) {
    val currentComponent = component.stack.subscribeAsState().value.active.instance

    //todo resources
    val title: String = when (currentComponent) {
        is MainComponent.ChildBottomNavigation.FavoritesNotes -> "Favorites"
        is MainComponent.ChildBottomNavigation.ListNotes -> "Your notes"
        is MainComponent.ChildBottomNavigation.Profile -> "Profile"
    }

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(
                onClick = component::openSettingsSlot
            ) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = null)
            }
        }
    )
}

@Composable
private fun NotesBottomNavigation(component: MainComponent) {

    val currentComponent = component.activeChildIndex.subscribeAsState()

    val bottomStrings = listOf("Notes", "Favorites", "Profile")

    NavigationBar {
        bottomStrings.forEachIndexed { index, string ->
            NavigationBarItem(
                selected = index == currentComponent.value,
                onClick = { component.onShelfSelect(index) },
                alwaysShowLabel = false,
                label = {
                    Text(text = string)
                },
                icon = {

                }
            )
        }
    }
}