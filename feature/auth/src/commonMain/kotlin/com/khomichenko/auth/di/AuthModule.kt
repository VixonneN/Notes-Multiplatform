package com.khomichenko.auth.di

import com.khomichenko.auth.component.AuthComponent
import com.khomichenko.auth.component.AuthComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val AuthModule = module {
    factoryOf(::AuthComponentImpl) { bind<AuthComponent>() }
}