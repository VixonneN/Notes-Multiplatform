@file:OptIn(ExperimentalMaterial3Api::class)

package com.khomichenko.edit_note.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khomichenko.edit_note.component.EditNoteComponent
import com.khomichenko.edit_note.component.EditNoteComponent.Model

@Composable
fun EditNoteScreen(component: EditNoteComponent) {

    Scaffold(
        topBar = { TopBar(component) }
    ) { paddingValues ->
        EditNoteBottomSheetContent(component, Modifier.padding(paddingValues))
    }
}

@Composable
private fun TopBar(component: EditNoteComponent) {
    TopAppBar(
        title = {
            Text(text = "ADD NOTE")
        },
        navigationIcon = {
            IconButton(
                onClick = component::dismissComponent
            ) {
                Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
            }
        }
    )
}

@Composable
fun EditNoteBottomSheetContent(component: EditNoteComponent, modifier: Modifier = Modifier) {
    val state = component.state.collectAsState(Model())

    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(text = state.value.title)
        Text(text = state.value.note)
        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "SAVE")
        }
    }
}
