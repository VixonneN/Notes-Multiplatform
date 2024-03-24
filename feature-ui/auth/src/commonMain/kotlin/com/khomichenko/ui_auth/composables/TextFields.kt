package com.khomichenko.ui_auth.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Shower
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.*

@Composable
internal fun OutlinedLoginField(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    onTextChange: (String) -> Unit,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Next,
        capitalization = KeyboardCapitalization.None,
        keyboardType = KeyboardType.Text,
        autoCorrect = false
    ),
    trailingIcon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    supportingText: @Composable (() -> Unit)? = null
) = OutlinedTextField(
    value = text,
    onValueChange = onTextChange,
    modifier = modifier,
    isError = isError,
    keyboardOptions = keyboardOptions,
    label = label?.let { { Text(label) } },
    colors = OutlinedFieldDefaults.Colors,
    shape = OutlinedFieldDefaults.Shape,
    singleLine = true,
    trailingIcon = trailingIcon,
    enabled = enabled,
    supportingText = supportingText
)

@Composable
fun OutlinedPasswordField(
    modifier: Modifier = Modifier,
    text: String,
    label: String? = null,
    supportingText: String? = null,
    onTextChange: (String) -> Unit,
    isError: Boolean = false,
    isPasswordVisible: Boolean = false,
    onTrailingClick: () -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.None,
        autoCorrect = false,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done
    ),
) = OutlinedTextField(
    value = text,
    onValueChange = onTextChange,
    modifier = modifier,
    isError = isError,
    keyboardOptions = keyboardOptions,
    label = label?.let { { Text(label) } },
    supportingText = supportingText?.let { { Text(supportingText) } },
    colors = OutlinedFieldDefaults.Colors,
    shape = OutlinedFieldDefaults.Shape,
    singleLine = true,
    trailingIcon = {
        IconButton(
            onClick = onTrailingClick,
            content = {
                Icon(
                    imageVector =
                    if (isPasswordVisible) Icons.Default.Shower
                    else Icons.Default.DeleteForever,
                    contentDescription =
                    if (isPasswordVisible) "Hide"
                    else "Show",
                )
            }
        )
    },
    visualTransformation = if (!isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None
)