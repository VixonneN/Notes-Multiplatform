package com.khomichenko.registration.store

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.khomichenko.network.repository.AuthRepository
import com.khomichenko.preferences.repository.PreferenceRepository
import com.khomichenko.registration.store.RegisterStore.*
import kotlinx.coroutines.launch

internal class RegisterStoreFactory(
    private val storeFactory: StoreFactory,
    private val preferenceRepository: PreferenceRepository,
    private val authRepository: AuthRepository
) {
    
    fun create() : RegisterStore = object : RegisterStore, Store<Intent, State, Label> by storeFactory.create(
        name = RegisterStore::class.simpleName,
        initialState = State(),
        bootstrapper = SimpleBootstrapper(Unit),
        executorFactory = ::ExecutorImpl,
        reducer = ReducerImpl
    ) {}
    
    private inner class ExecutorImpl: CoroutineExecutor<Intent, Unit, State, Result, Label>() {
        override fun executeIntent(intent: Intent,) {
            when(intent) {
                is Intent.ChangeLogin -> dispatch(Result.LoginChanged(intent.login))
                is Intent.ChangePassword -> dispatch(Result.PasswordChanged(intent.password))
                Intent.ChangePasswordVisibility -> dispatch(Result.PasswordVisibilityChanged)
                is Intent.SetLoginError -> dispatch(Result.LoginErrorChanged(intent.isError))
                is Intent.SetPasswordError -> dispatch(Result.PasswordErrorChanged(intent.isError))
                Intent.TryLogin -> scope.launch { tryLogin(state()) }
            }
        }
        
        private suspend fun tryLogin(getState: State) {
            if (!getState.isLoginError && !getState.isPasswordError) {
                val login = getState.login
                val password = getState.password

                authRepository.auth(login, password)
                    .onSuccess { response ->
                        response?.let {
                            preferenceRepository.setUserToken(it)
                        }
                    }
                    .onFailure {
                        publish(Label.ShowSnackbarError)
                    }
            }
        }
    }
    
    private object ReducerImpl: Reducer<State, Result> {
        override fun State.reduce(msg: Result): State =
            when(msg) {
                is Result.LoginChanged -> copy(login = msg.login)
                is Result.PasswordChanged -> copy(password = msg.password)
                Result.PasswordVisibilityChanged -> copy(passwordVisibility = !passwordVisibility)
                is Result.LoginErrorChanged -> copy(isLoginError = msg.isError)
                is Result.PasswordErrorChanged -> copy(isPasswordError = msg.isError)
            }

    }
}