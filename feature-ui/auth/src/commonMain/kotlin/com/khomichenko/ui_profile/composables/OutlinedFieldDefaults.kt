package com.khomichenko.ui_profile.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

object OutlinedFieldDefaults {

    val Shape = RoundedCornerShape(12.dp)

    val Colors
        @Composable
        get() = OutlinedTextFieldDefaults.colors(
            cursorColor = colorScheme.primary,
            focusedTextColor = colorScheme.onBackground,
            focusedBorderColor = colorScheme.primary,
            focusedLabelColor = colorScheme.primary,
            focusedSupportingTextColor = colorScheme.primary,
            focusedTrailingIconColor = colorScheme.onBackground,
            unfocusedTextColor = colorScheme.onBackground,
            unfocusedBorderColor = colorScheme.outline,
            unfocusedLabelColor = colorScheme.onSecondaryContainer,
            unfocusedTrailingIconColor = colorScheme.onBackground,
            errorTextColor = colorScheme.onBackground,
            errorCursorColor = colorScheme.error,
            errorBorderColor = colorScheme.error,
            errorLabelColor = colorScheme.error,
            errorSupportingTextColor = colorScheme.error,
            errorTrailingIconColor = colorScheme.onBackground,
            disabledTextColor = colorScheme.onSecondaryContainer,
            disabledBorderColor = colorScheme.onSecondaryContainer,
            disabledLabelColor = colorScheme.onSecondaryContainer,
            disabledSupportingTextColor = colorScheme.onSecondaryContainer,
            disabledTrailingIconColor = colorScheme.onSecondaryContainer,
            )
}