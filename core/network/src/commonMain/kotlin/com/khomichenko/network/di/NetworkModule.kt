package com.khomichenko.network.di

import com.khomichenko.network.repository.AuthRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NetworkModule = module {
    singleOf(::provideAuth)
    singleOf(::AuthRepository)
}

private fun provideAuth() = Firebase.auth