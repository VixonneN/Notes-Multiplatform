package com.khomichenko.profile.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.khomichenko.auth.component.AuthComponent
import com.khomichenko.registration.component.RegistrationComponent

interface ProfileRootComponent {

    sealed interface Child {
        data class Auth(val component: AuthComponent) : Child
        data class Registration(val component: RegistrationComponent): Child
        data class Profile(val component: ComponentContext) : Child
    }

    val stack: Value<ChildStack<*, Child>>

    fun moveToRegistration()
    fun moveToProfile()
}