package com.khomichenko.network.repository

class AuthRepository() {

    suspend fun login(email: String, password: String) = runCatching {
    }

    suspend fun auth(email: String, password: String) = runCatching {
    }

    suspend fun logOut() = runCatching {
    }
}