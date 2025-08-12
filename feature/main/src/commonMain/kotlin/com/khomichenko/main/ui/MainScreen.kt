@file:OptIn(ExperimentalMaterial3Api::class)

package com.khomichenko.main.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.khomichenko.edit_note.ui.EditNoteScreen
import com.khomichenko.favorites.ui.FavoritesScreen
import com.khomichenko.main.component.MainComponent
import com.khomichenko.main.component.MainComponent.ChildBottomNavigation
import com.khomichenko.main.component.MainComponent.SlotChild
import com.khomichenko.ui.components.BottomSheet3Layout
import com.khomichenko.ui.components.rememberSlotModalBottomSheet3State
import com.khomichenko.add_note.ui.AddNoteScreen
import com.khomichenko.notes.ui.ListNotesScreen
import com.khomichenko.profile.ui.ProfileRootScreen
import com.khomichenko.settings.ui.SettingsScreen

@Composable
fun MainScreen(component: MainComponent) {
    val state = rememberSlotModalBottomSheet3State(
        slot = component.slot,
        sheetContent = {
            when (val child = it.instance) {
                is SlotChild.AddNote -> AddNoteScreen(child.component)
                is SlotChild.Settings -> SettingsScreen(child.component)
                is SlotChild.ShowNote -> EditNoteScreen(child.component)
            }
        }
    )

    Scaffold(
        topBar = { MainTopBar(component) },
        bottomBar = { NotesBottomNavigation(component) }
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val child = it.instance) {
                is ChildBottomNavigation.ListNotes -> ListNotesScreen(child.component)
                is ChildBottomNavigation.FavoritesNotes -> FavoritesScreen(child.component)
                is ChildBottomNavigation.Profile -> ProfileRootScreen(child.component)
            }
        }
    }

    BottomSheet3Layout(
        isVisible = state.isVisible.value,
        content = state.sheetContent.value,
        onDismiss = remember { { component.dismissSlotChild() } },
        dragHandle = null
    )
}

@Composable
private fun MainTopBar(component: MainComponent) {
    val currentComponent = component.stack.subscribeAsState().value.active.instance

    //todo resources
    val title: String = when (currentComponent) {
        is ChildBottomNavigation.FavoritesNotes -> "Favorites"
        is ChildBottomNavigation.ListNotes -> "Your notes"
        is ChildBottomNavigation.Profile -> "Profile"
    }

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            IconButton(
                onClick = component::openSettingsSlot
            ) {
                Icon(imageVector = Icons.Default.Close, contentDescription = "close")
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
                label = {
                    Text(text = string)
                },
                icon = {

                }
            )
        }
    }
}