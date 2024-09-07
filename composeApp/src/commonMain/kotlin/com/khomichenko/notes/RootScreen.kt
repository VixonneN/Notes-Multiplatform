package com.khomichenko.notes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Shapes as MaterialShapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.khomichenko.notes.theme.DarkColorScheme
import com.khomichenko.notes.theme.LightColorScheme
import com.khomichenko.root.component.RootComponent
import com.khomichenko.ui_profile.AuthScreen
import com.khomichenko.ui_main.MainScreen
import com.khomichenko.ui_onboarding.OnboardingScreen
import com.khomichenko.ui_registration.RegistrationScreen
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveTheme
import io.github.alexzhirkevich.cupertino.adaptive.CupertinoThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import io.github.alexzhirkevich.cupertino.adaptive.MaterialThemeSpec
import io.github.alexzhirkevich.cupertino.adaptive.Theme
import io.github.alexzhirkevich.cupertino.theme.darkColorScheme
import io.github.alexzhirkevich.cupertino.theme.lightColorScheme
import io.github.alexzhirkevich.cupertino.theme.Shapes as CupertinoShapes

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
internal fun RootScreen(rootComponent: RootComponent) = GeneratedAdaptiveTheme(
    target = Theme.Cupertino,
    primaryColor = Color.LightGray
) {
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


@ExperimentalAdaptiveApi
@Composable
fun GeneratedAdaptiveTheme(
    target: Theme,
    primaryColor: Color,
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    shapes: MaterialShapes = MaterialShapes(),
    content: @Composable () -> Unit
) {
    AdaptiveTheme(
        target = target,
        material = MaterialThemeSpec.Default(
            colorScheme = if (useDarkTheme) DarkColorScheme else LightColorScheme,
            shapes = MaterialShapes(
                extraSmall = shapes.extraSmall,
                small = shapes.small,
                medium = shapes.medium,
                large = shapes.large,
                extraLarge = shapes.extraLarge
            )
        ),
        cupertino = CupertinoThemeSpec.Default(
            colorScheme = if (useDarkTheme)
                darkColorScheme(accent = primaryColor)
            else lightColorScheme(accent = primaryColor),
            shapes = CupertinoShapes(
                extraSmall = shapes.extraSmall,
                small = shapes.small,
                medium = shapes.medium,
                large = shapes.large,
                extraLarge = shapes.extraLarge
            )
        ),
        content = content
    )
}