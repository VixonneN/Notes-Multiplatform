package com.khomichenko.database.room.di

import com.khomichenko.database.room.dao.NoteDao
import com.khomichenko.database.room.database.AppDatabase
import com.khomichenko.database.room.repository.NotesDatabaseRepository
import com.khomichenko.database.room.repository.NotesDatabaseRepositoryImpl
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseRoomModule = module {
    includes(databaseActualModule())

    singleOf(::provideDao)

    singleOf(::NotesDatabaseRepositoryImpl) bind NotesDatabaseRepository::class
}

internal expect fun databaseActualModule() : Module

internal fun provideDao(database: AppDatabase) : NoteDao =
    database.getDao()