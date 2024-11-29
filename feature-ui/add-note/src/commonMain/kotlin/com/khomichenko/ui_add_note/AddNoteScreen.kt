@file:OptIn(ExperimentalMaterial3Api::class)

package com.khomichenko.ui_add_note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khomichenko.add_note.component.AddNoteComponent

@Composable
fun AddNoteScreen(component: AddNoteComponent) {
    Box(modifier = Modifier.fillMaxSize()) {
        AddNoteBottomSheetContent(component)
    }
}

@Composable
private fun TopBar(component: AddNoteComponent) {
    TopAppBar(
        title = {
            Text(text = "ADD NOTE")
        },
        navigationIcon = {
            IconButton(
                onClick = component::saveNote
            ) {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun AddNoteBottomSheetContent(component: AddNoteComponent) {
    val state = component.state.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        TextField(
            value = state.value.title,
            onValueChange = component::changeTitle,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = state.value.note,
            onValueChange = component::changeNote,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = component::saveNote,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "SAVE")
        }
    }
}