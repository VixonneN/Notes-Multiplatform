package com.khomichenko.registration.di

import com.khomichenko.registration.component.RegistrationComponent
import com.khomichenko.registration.component.RegistrationComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val RegistrationModule = module {
    factoryOf(::RegistrationComponentImpl) { bind<RegistrationComponent>() }
}