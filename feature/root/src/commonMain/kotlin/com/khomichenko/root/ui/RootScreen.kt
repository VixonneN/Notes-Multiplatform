package com.khomichenko.root.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.khomichenko.root.component.RootComponent
import com.khomichenko.ui.theme.CustomNotesTheme
import com.khomichenko.main.ui.MainScreen
import com.khomichenko.onboarding.ui.OnboardingScreen
import com.khomichenko.auth.ui.AuthScreen
import com.khomichenko.registration.ui.RegistrationScreen

@Composable
fun RootScreen(rootComponent: RootComponent) = CustomNotesTheme {
    Children(
        stack = rootComponent.stack,
        animation = stackAnimation(slide())
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Auth -> AuthScreen(child.component)
            is RootComponent.Child.Main -> MainScreen(child.component)
            is RootComponent.Child.Onboarding -> OnboardingScreen(child.component)
            is RootComponent.Child.Registration -> RegistrationScreen(child.component)
        }
    }
}

//todo in core:utils
val MainLifecycleOwner = compositionLocalOf<LifecycleOwner> { error("No lifecycle found") }
