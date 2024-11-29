package com.khomichenko.registration.component

import com.khomichenko.registration.store.RegisterStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RegistrationComponent {
    val model: StateFlow<RegisterStore.State>
    val events: Flow<RegisterStore.Label>

    fun changeLogin(login: String)
    fun changePassword(password: String)
    fun changePasswordVisibility()
    fun login()

}