package com.khomichenko.database.room.di

import androidx.room.RoomDatabase
import androidx.sqlite.SQLiteDriver
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.khomichenko.database.room.dao.NoteDao
import com.khomichenko.database.room.database.AppDatabase
import com.khomichenko.database.room.repository.NotesDatabaseRepository
import com.khomichenko.database.room.repository.NotesDatabaseRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseRoomModule = module {
    includes(databaseActualModule())

    singleOf(::getRoomDatabase)
    singleOf(::provideDao)
    singleOf(::NotesDatabaseRepositoryImpl) bind NotesDatabaseRepository::class
}

private fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
) : AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}

internal expect fun databaseActualModule() : Module

internal fun provideDao(database: AppDatabase) : NoteDao =
    database.getDao()
