package com.khomichenko.auth.store

import com.arkivanov.mvikotlin.core.store.Store
import com.khomichenko.auth.store.AuthStore.*

//todo
interface AuthStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class ChangeLogin(val login: String) : Intent
        data class SetLoginError(val isError: Boolean) : Intent
        data class ChangePassword(val password: String) : Intent
        data class SetPasswordError(val isError: Boolean) : Intent
        data object ChangePasswordVisibility : Intent
        data object TryLogin : Intent
    }

    sealed interface Result {
        data class LoginChanged(val login: String) : Result
        data class LoginErrorChanged(val isError: Boolean): Result
        data class PasswordChanged(val password: String) : Result
        data class PasswordErrorChanged(val isError: Boolean): Result
        
        data object PasswordVisibilityChanged : Result
    }

    sealed interface Label {
        data object ShowSnackbarError: Label
    }

    data class State(
        val login: String = "",
        val password: String = "",
        val passwordVisibility: Boolean = false,
        val isLoginError: Boolean = false,
        val isPasswordError: Boolean = false,
    )

}