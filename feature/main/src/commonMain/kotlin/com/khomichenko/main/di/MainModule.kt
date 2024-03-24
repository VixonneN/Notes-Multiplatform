package com.khomichenko.main.di

import com.khomichenko.main.component.MainComponent
import com.khomichenko.main.component.MainComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val MainModule = module {
    factoryOf(::MainComponentImpl) { bind<MainComponent>() }
}