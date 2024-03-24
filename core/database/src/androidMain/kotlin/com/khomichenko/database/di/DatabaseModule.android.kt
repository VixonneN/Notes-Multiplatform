package com.khomichenko.database.di

import com.khomichenko.database.driver.DatabaseDriverFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

actual fun databaseExpectModule() = module {
    single { DatabaseDriverFactory(context = androidApplication()) }
}
