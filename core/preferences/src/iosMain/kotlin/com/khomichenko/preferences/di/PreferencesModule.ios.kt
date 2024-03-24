package com.khomichenko.preferences.di

import com.khomichenko.preferences.repository.PreferenceIosRepository
import com.khomichenko.preferences.repository.PreferenceRepository
import org.koin.core.module.Module
import org.koin.dsl.module

actual val PreferencesModule: Module = module {
    single<PreferenceRepository> { PreferenceIosRepository() }
}