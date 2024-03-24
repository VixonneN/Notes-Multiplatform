package com.khomichenko.notes.di

import com.khomichenko.notes.component.NotesComponent
import com.khomichenko.notes.component.NotesComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val NotesFeatureModule = module {
    factoryOf(::NotesComponentImpl) { bind<NotesComponent>() }
}