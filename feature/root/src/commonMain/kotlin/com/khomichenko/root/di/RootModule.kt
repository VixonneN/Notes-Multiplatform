package com.khomichenko.root.di

import com.khomichenko.root.component.RootComponent
import com.khomichenko.root.component.RootComponentImpl
import org.koin.dsl.module

val RootModule = module {
    factory<RootComponent> { RootComponentImpl(componentContext = get()) }
}