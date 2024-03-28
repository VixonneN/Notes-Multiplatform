package com.khomichenko.edit_note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khomichenko.edit_note.component.EditNoteComponent
import com.khomichenko.edit_note.component.EditNoteComponent.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreen(component: EditNoteComponent) {
    ModalBottomSheet(
        onDismissRequest = component::dismissComponent,
        modifier = Modifier.fillMaxWidth(),
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        content = {
            Box(modifier = Modifier.fillMaxSize()) {
                AddNoteBottomSheetContent(component)
            }
        }
    )
}

@Composable
private fun AddNoteBottomSheetContent(component: EditNoteComponent) {
    val state = component.state.collectAsState(Model())

    Column(
        modifier = Modifier
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
