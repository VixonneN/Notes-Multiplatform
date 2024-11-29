package com.khomichenko.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.khomichenko.auth.component.AuthComponent
import com.khomichenko.main.component.MainComponent
import com.khomichenko.onboarding.component.OnboardingComponent
import com.khomichenko.registration.component.RegistrationComponent
import com.khomichenko.root.store.RootStore.Label
import kotlinx.coroutines.flow.Flow

interface RootComponent : ComponentContext {

    val stack: Value<ChildStack<*, Child>>

    fun clearOnboarding()
    val label: Flow<Label>

    sealed interface Child {
        data class Onboarding(val component: OnboardingComponent) : Child
        data class Auth(val component: AuthComponent) : Child
        data class Registration(val component: RegistrationComponent) : Child
        data class Main(val component: MainComponent) : Child
    }
}