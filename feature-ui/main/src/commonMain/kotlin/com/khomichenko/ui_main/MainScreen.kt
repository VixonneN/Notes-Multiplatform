@file:OptIn(ExperimentalCupertinoApi::class, ExperimentalAdaptiveApi::class)

package com.khomichenko.ui_main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import com.khomichenko.ui_profile.ProfileRootScreen
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveIconButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBar
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveNavigationBarItem
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.Settings

@Composable
fun MainScreen(component: MainComponent) {
    AdaptiveScaffold(
        bottomBar = {
            NotesBottomNavigation(component)
        },
        topBar = {
            MainTopBar(component)
        }
    ) { paddingValues ->
        Children(
            stack = component.stack,
            modifier = Modifier.padding(paddingValues)
        ) {
            when (val child = it.instance) {
                is MainComponent.ChildBottomNavigation.ListNotes -> ListNotesScreen(child.component)
                is MainComponent.ChildBottomNavigation.FavoritesNotes -> FavoritesScreen(child.component)
                is MainComponent.ChildBottomNavigation.Profile -> ProfileRootScreen(child.component)
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

@Composable
private fun MainTopBar(component: MainComponent) {
    val currentComponent = component.stack.subscribeAsState().value.active.instance

    //todo resources
    val title: String = when (currentComponent) {
        is MainComponent.ChildBottomNavigation.FavoritesNotes -> "Favorites"
        is MainComponent.ChildBottomNavigation.ListNotes -> "Your notes"
        is MainComponent.ChildBottomNavigation.Profile -> "Profile"
    }

    AdaptiveTopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
            AdaptiveIconButton(
                onClick = component::openSettingsSlot
            ) {
                Icon(imageVector = AdaptiveIcons.Outlined.Settings, contentDescription = null)
            }
        }

    )
}

@Composable
private fun NotesBottomNavigation(component: MainComponent) {

    val currentComponent = component.activeChildIndex.subscribeAsState()

    val bottomStrings = listOf("Notes", "Favorites", "Profile")

    AdaptiveNavigationBar {
        bottomStrings.forEachIndexed { index, s ->
            AdaptiveNavigationBarItem(
                selected = index == currentComponent.value,
                onClick = { component.onShelfSelect(index) },
                alwaysShowLabel = false,
                label = {
                    Text(text = s)
                },
                icon = {

                }
            )
        }
    }
}