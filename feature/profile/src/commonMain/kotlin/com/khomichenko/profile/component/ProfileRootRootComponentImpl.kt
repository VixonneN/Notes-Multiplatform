package com.khomichenko.profile.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.khomichenko.auth.component.AuthComponent
import com.khomichenko.profile.component.ProfileRootComponent.*
import com.khomichenko.registration.component.RegistrationComponent
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

internal class ProfileRootRootComponentImpl(
    componentContext: ComponentContext,
) : ProfileRootComponent, KoinComponent, ComponentContext by componentContext {

    private fun auth(componentContext: ComponentContext) = get<AuthComponent>(
        parameters = {
            parametersOf(
                componentContext,
            )
        }
    )

    private fun registration(componentContext: ComponentContext) = get<RegistrationComponent>(
        parameters = {
            parametersOf(
                componentContext,
            )
        }
    )

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, Child>> =
        childStack(
            source = navigation,
            initialConfiguration = Config.Auth,
            serializer = Config.serializer(),
            handleBackButton = false,
        ) { configuration, componentContext ->
            when(configuration) {
                Config.Auth -> Child.Auth(auth(componentContext))
                Config.Profile -> Child.Profile(componentContext)
                Config.Registration -> Child.Registration(registration(componentContext))
            }
        }

    override fun moveToRegistration() {
        navigation.pushNew(Config.Registration)
    }

    override fun moveToProfile() {
        navigation.pushNew(Config.Profile)
    }

    @Serializable
    sealed interface Config {
        @Serializable
        data object Auth: Config

        @Serializable
        data object Registration: Config

        @Serializable
        data object Profile: Config
    }

}