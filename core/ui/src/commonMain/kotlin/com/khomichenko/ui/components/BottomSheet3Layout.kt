package com.khomichenko.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet3Layout(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
    onDismiss: () -> Unit,
    cancelable: Boolean = true,
    dragHandle: @Composable (() -> Unit)? = null, //{ BottomSheetClip() },
    content: @Composable ColumnScope.() -> Unit,
) {
    ModalSheet(
        modifier = modifier.statusBarsPadding(),
        visible = isVisible,
        cancelable = cancelable,
        onVisibleChange = {
            if (!it) onDismiss()
        },
        content = {
            Column(modifier = Modifier.safeContentPadding()) {
                dragHandle?.invoke()
                content()
            }
        }
    )
}
