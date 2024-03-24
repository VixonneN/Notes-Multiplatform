package com.khomichenko.auth.component

import com.khomichenko.auth.store.AuthStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface AuthComponent {
    
    val model: StateFlow<AuthStore.State>
    val events: Flow<AuthStore.Label>
    
    fun changeLogin(login: String)
    fun changePassword(password: String)
    fun changePasswordVisibility()
    fun login()
}