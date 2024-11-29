
package com.khomichenko.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
internal fun ModalSheet(
    visible: Boolean,
    onVisibleChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    cancelable: Boolean = true,
    skipHalfExpanded: Boolean = true,
    shape: Shape = BottomSheetDefaults.ExpandedShape,
    elevation: Dp = BottomSheetDefaults.Elevation,
    containerColor: Color = BottomSheetDefaults.ContainerColor,
    contentColor: Color = contentColorFor(containerColor),
    scrimColor: Color = BottomSheetDefaults.ScrimColor,
    content: @Composable ColumnScope.() -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = skipHalfExpanded,
        confirmValueChange = {
            // Intercept and disallow hide gesture / action
            if (it == SheetValue.Hidden && !cancelable) {
                return@rememberModalBottomSheetState false
            } else {
                true
            }
        },
    )
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(visible) {
        if (visible) {
            sheetState.show()
        } else {
            sheetState.hide()
        }
    }

    if (!visible && sheetState.currentValue == sheetState.targetValue && !sheetState.isVisible) {
        return
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            if (cancelable) {
                coroutineScope.launch {
                    sheetState.hide()
                    onVisibleChange(false)
                }
            }
        },
        modifier = modifier,
        shape = shape,
        dragHandle = null,
        tonalElevation = elevation,
        containerColor = containerColor,
        contentColor = contentColor,
        scrimColor = scrimColor,
        content = content
    )
}