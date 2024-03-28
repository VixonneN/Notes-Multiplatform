package com.khomichenko.ui_note

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
import com.khomichenko.notes.component.NotesComponent
import com.khomichenko.notes.component.NotesComponent.*
import com.khomichenko.notes.mapper.Note

@Composable
fun ListNotesScreen(component: NotesComponent) {
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = note.title)
                    Text(text = note.note)
                }
            }
        }
    }
}