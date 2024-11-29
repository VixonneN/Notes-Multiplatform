package com.khomichenko.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.Child
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.delay

val emptyContent: @Composable ColumnScope.() -> Unit = {
    Spacer(Modifier.height(1.dp))
}

class SlotModalBottomSheet3State(
    val sheetContent: State<@Composable ColumnScope.() -> Unit>,
    val isVisible: State<Boolean>,
)

@Composable
fun <C : Any, T : Any> rememberSlotModalBottomSheet3State(
    slot: Value<ChildSlot<C, T>>,
    sheetContent: @Composable (child: Child.Created<C, T>) -> Unit,
): SlotModalBottomSheet3State {
    val slotState by slot.subscribeAsState()
    val child: Child.Created<C, T>? = slotState.child

    val childContent = remember { mutableStateOf(emptyContent) }
    val isVisible = remember { mutableStateOf(false) }

    LaunchedEffect(child) {
        if (child != null) {
            childContent.value = { sheetContent(child) }
        }
    }

    LaunchedEffect(child == null) {
        if (child == null) {
            isVisible.value = false
            delay(BottomSheetAnimationDuration)
            childContent.value = { emptyContent }
        } else {
            isVisible.value = true
        }
    }

    return remember {
        SlotModalBottomSheet3State(
            sheetContent = childContent,
            isVisible = isVisible,
        )
    }
}

private const val BottomSheetAnimationDuration = 400L

//SheetDefaults.kt
//private val BottomSheetAnimationSpec: AnimationSpec<Float> =
//    tween(durationMillis = 300, easing = FastOutSlowInEasing)