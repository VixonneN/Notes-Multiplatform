package com.khomichenko.add_note.di

import com.khomichenko.add_note.component.AddNoteComponent
import com.khomichenko.add_note.component.AddNoteComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val AddNoteFeatureModule = module {
    factoryOf(::AddNoteComponentImpl) { bind<AddNoteComponent>() }
}