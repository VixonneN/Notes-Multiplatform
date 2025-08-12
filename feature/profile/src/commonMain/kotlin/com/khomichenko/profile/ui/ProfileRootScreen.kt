package com.khomichenko.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.khomichenko.auth.ui.AuthScreen
import com.khomichenko.profile.component.ProfileRootComponent
import com.khomichenko.registration.ui.RegistrationScreen

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
            when (val child = it.instance) {
                is ProfileRootComponent.Child.Auth -> AuthScreen(child.component)
                is ProfileRootComponent.Child.Profile -> Box(
                    modifier = Modifier.fillMaxSize().background(
                        Color.Blue
                    )
                )
                is ProfileRootComponent.Child.Registration -> RegistrationScreen(child.component)
            }
        }
    }
}
