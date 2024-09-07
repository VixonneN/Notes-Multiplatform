package com.khomichenko.network.repository

import dev.gitlive.firebase.auth.FirebaseAuth

class AuthRepository(
    private val auth: FirebaseAuth,
) {

    suspend fun login(email: String, password: String) = runCatching {
        auth.signInWithEmailAndPassword(email,password).user?.uid
    }

    suspend fun auth(email: String, password: String) = runCatching {
        auth.createUserWithEmailAndPassword(email, password).user?.uid
    }

    suspend fun logOut() = runCatching {
        auth.signOut()
    }
}