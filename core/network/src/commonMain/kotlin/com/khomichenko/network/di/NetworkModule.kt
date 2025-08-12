package com.khomichenko.network.di

import com.khomichenko.network.repository.AuthRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val NetworkModule = module {
    singleOf(::AuthRepository)
}
