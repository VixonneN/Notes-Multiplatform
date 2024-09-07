@file:OptIn(ExperimentalCupertinoApi::class, ExperimentalAdaptiveApi::class)

package com.khomichenko.edit_note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khomichenko.edit_note.component.EditNoteComponent
import com.khomichenko.edit_note.component.EditNoteComponent.Model
import io.github.alexzhirkevich.cupertino.CupertinoBottomSheetContent
import io.github.alexzhirkevich.cupertino.ExperimentalCupertinoApi
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveIconButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTopAppBar
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.Close

@Composable
fun EditNoteScreen(component: EditNoteComponent) {
    CupertinoBottomSheetContent(
        topBar = {
            TopBar(component)
        }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AddNoteBottomSheetContent(component)
        }
    }
}

@Composable
private fun TopBar(component: EditNoteComponent) {
    AdaptiveTopAppBar(
        title = {
            Text(text = "ADD NOTE")
        },
        navigationIcon = {
            AdaptiveIconButton(
                onClick = component::dismissComponent
            ) {
                Icon(imageVector = AdaptiveIcons.Outlined.Close, contentDescription = null)
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
        AdaptiveButton(
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "SAVE")
        }
    }
}
