package com.khomichenko.edit_note.di

import com.khomichenko.edit_note.component.EditNoteComponent
import com.khomichenko.edit_note.component.EditNoteComponentImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val EditNoteModule = module {
    factoryOf(::EditNoteComponentImpl) { bind<EditNoteComponent>() }
}