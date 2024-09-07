package com.khomichenko.notes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.khomichenko.notes.theme.AppTheme
import com.khomichenko.root.component.RootComponent
import com.khomichenko.ui_profile.AuthScreen
import com.khomichenko.ui_main.MainScreen
import com.khomichenko.ui_onboarding.OnboardingScreen
import com.khomichenko.ui_registration.RegistrationScreen

@Composable
internal fun RootScreen(rootComponent: RootComponent) = AppTheme {
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


