package com.khomichenko.favorites

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.khomichenko.favorites.component.FavoritesComponent
import com.khomichenko.favorites.component.FavoritesComponent.Model
import com.khomichenko.favorites.mapper.Note

@Composable
fun FavoritesScreen(component: FavoritesComponent) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = component::openBottomSheetComponent
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) {
        val state = component.state.collectAsState(Model())
        LazyColumn {
            items(state.value.listNotes) { note: Note ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { component.openEditSlotComponent(note.id) }
                ) {
                    Text(text = note.title)
                    Text(text = note.note)
                }
            }
        }
    }
}
