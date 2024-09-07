package com.khomichenko.registration.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.khomichenko.registration.store.RegisterStore.Intent
import com.khomichenko.registration.store.RegisterStore.Label
import com.khomichenko.registration.store.RegisterStore.State
import com.khomichenko.registration.store.RegisterStoreFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

internal class RegistrationComponentImpl(
    componentContext: ComponentContext
) : RegistrationComponent, KoinComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore {
        RegisterStoreFactory(
            storeFactory = get(),
            preferenceRepository = get(),
            authRepository = get()
        ).create()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<State>
        get() = store.stateFlow

    override val events: Flow<Label>
        get() = store.labels

    override fun changeLogin(login: String) {
        store.accept(Intent.ChangeLogin(login))
    }

    override fun changePassword(password: String) {
        store.accept(Intent.ChangePassword(password))
    }

    override fun changePasswordVisibility() {
        store.accept(Intent.ChangePasswordVisibility)
    }

    override fun login() {
        store.accept(Intent.TryLogin)
    }
}