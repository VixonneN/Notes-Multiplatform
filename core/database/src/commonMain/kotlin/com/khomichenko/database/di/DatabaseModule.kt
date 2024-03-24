package com.khomichenko.database.di

import com.khomichenko.database.db.NotesCacheDBO
import com.khomichenko.database.db.notesCacheDatabase
import com.khomichenko.database.driver.DatabaseDriverFactory
import com.khomichenko.database.repository.NotesDatabaseRepository
import com.khomichenko.database.repository.NotesDatabaseRepositoryImpl
import com.khomichenko.database.utils.intLongColumnAdapter
import com.khomichenko.database.utils.stringColumnAdapter
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun databaseExpectModule(): Module

val DatabaseCommonModule = module {
    includes(databaseExpectModule())

    single { notesCacheDatabase(
        driver = get<DatabaseDriverFactory>().createDriver(),
        notesCacheDBOAdapter = NotesCacheDBO.Adapter(
            idAdapter = intLongColumnAdapter,
            titleAdapter = stringColumnAdapter,
            noteAdapter = stringColumnAdapter
        )
    ) }

    single<NotesDatabaseRepository> { NotesDatabaseRepositoryImpl(database = get()) }
}