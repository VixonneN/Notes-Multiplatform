package com.khomichenko.ui_profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.khomichenko.profile.component.ProfileRootComponent
import com.khomichenko.ui_registration.RegistrationScreen

@Composable
fun ProfileRootScreen(component: ProfileRootComponent) {
    val snackbarState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarState) }
    ) { paddingValues ->
        Children(
            stack = component.stack.value,
            modifier = Modifier.padding(paddingValues)
        ) {
            when(val child = it.instance) {
                is ProfileRootComponent.Child.Auth -> AuthScreen(child.component)
                is ProfileRootComponent.Child.Profile -> TODO()
                is ProfileRootComponent.Child.Registration -> RegistrationScreen(child.component)
            }
        }
    }
}
