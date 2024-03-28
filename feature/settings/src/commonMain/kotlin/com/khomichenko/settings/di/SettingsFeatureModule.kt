package com.khomichenko.settings.di

import com.khomichenko.settings.component.SettingsComponent
import com.khomichenko.settings.component.SettingsComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val SettingsFeatureModule = module {
    factoryOf(::SettingsComponentImpl) { bind<SettingsComponent>() }
}