package com.khomichenko.ui_auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khomichenko.auth.component.AuthComponent
import com.khomichenko.auth.store.AuthStore
import com.khomichenko.ui_auth.composables.OutlinedLoginField
import com.khomichenko.ui_auth.composables.OutlinedPasswordField

@Composable
fun AuthScreen(component: AuthComponent) {
    val snackbarState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState) }
    ) {
        AuthContent(component, Modifier.padding(it))
        
        LaunchedEffect(component.events) {
            component.events.collect { event ->
                when(event) {
                    AuthStore.Label.ShowSnackbarError -> snackbarState.showSnackbar("Some error")
                }
            }
        }
    }
}

@Composable
private fun AuthContent(component: AuthComponent, modifier: Modifier) {
    val state = component.model.collectAsState().value
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 21.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedLoginField(
                text = state.login,
                onTextChange = component::changeLogin,
                modifier = Modifier.fillMaxWidth(),
                isError = state.isLoginError,
                supportingText = {
                    if (state.isLoginError) {
                        Text(text = "ti putin")
                    }
                }
            )
            
            OutlinedPasswordField(
                text = state.password,
                onTextChange = component::changePassword,
                modifier = Modifier.fillMaxWidth(),
                isError = state.isPasswordError,
                supportingText = "ti putin",
                onTrailingClick = component::changePasswordVisibility,
            )
            
            Button(
                onClick = component::login,
            ) {
                Text(text = "LOGIN")
            }
        }
    }
}