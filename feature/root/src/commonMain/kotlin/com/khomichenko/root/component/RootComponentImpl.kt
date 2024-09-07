package com.khomichenko.root.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.khomichenko.auth.component.AuthComponent
import com.khomichenko.main.component.MainComponent
import com.khomichenko.onboarding.component.OnboardingComponent
import com.khomichenko.registration.component.RegistrationComponent
import com.khomichenko.root.component.RootComponent.Child
import com.khomichenko.root.store.RootStore.Intent
import com.khomichenko.root.store.RootStore.Label
import com.khomichenko.root.store.RootStoreFactory
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

internal class RootComponentImpl(
    private val componentContext: ComponentContext
) : KoinComponent, RootComponent, ComponentContext by componentContext {

    private fun auth(componentContext: ComponentContext) = get<AuthComponent>(
        parameters = {
            parametersOf(
                componentContext,
            )
        }
    )

    private fun main(componentContext: ComponentContext) = get<MainComponent>(
        parameters = { parametersOf(componentContext) }
    )

    private fun onboarding(componentContext: ComponentContext) = get<OnboardingComponent>(
        parameters = {
            parametersOf(
                componentContext,
                ::navigateToAuth,
                ::navigateToMain
            )
        }
    )

    private fun registration(componentContext: ComponentContext) = get<RegistrationComponent>(
        parameters = { parametersOf(componentContext) }
    )


    private val navigation = StackNavigation<Config>()

    private val _stack = childStack(
        source = navigation,
        serializer = Config.serializer(),
        initialConfiguration = Config.Onboarding,
        handleBackButton = true
    ) { configuration, componentContext ->
        when (configuration) {
            Config.Auth -> Child.Auth(auth(componentContext))
            Config.Main -> Child.Main(main(componentContext))
            Config.Onboarding -> Child.Onboarding(onboarding(componentContext))
            Config.Registration -> Child.Registration(registration(componentContext))
        }
    }

    override val stack: Value<ChildStack<*, Child>>
        get() = _stack

    private val store = instanceKeeper.getStore {
        RootStoreFactory(
            storeFactory = get(),
            preferenceRepository = get()
        ).create()
    }

    override fun clearOnboarding() {
        store.accept(Intent.ChangeOnboardingCleared)
    }

    override val label: Flow<Label>
        get() = store.labels

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Onboarding : Config

        @Serializable
        data object Auth : Config

        @Serializable
        data object Registration : Config

        @Serializable
        data object Main : Config
    }

    private fun navigateToMain() {
        store.accept(Intent.ChangeOnboardingCleared)
        navigation.pushNew(Config.Main)
    }
    private fun navigateToAuth() = navigation.pushNew(Config.Auth)
    private fun navigateToRegistration() = navigation.pushNew(Config.Registration)
    private fun navigateToOnboarding() = navigation.pushNew(Config.Onboarding)
}

fun LifecycleOwner.scope() : CoroutineScope {
    val coroutineScope = CoroutineScope(CoroutineName("test"))
    lifecycle.doOnDestroy { coroutineScope.cancel() }
    return coroutineScope
}