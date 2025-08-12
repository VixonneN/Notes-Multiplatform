package com.khomichenko.database.room.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.khomichenko.database.room.database.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

internal actual fun databaseActualModule() = module {
    single { getDatabaseBuilder(ctx = androidApplication() ) }
}

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<AppDatabase> {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("my_room.db")
    return Room.databaseBuilder<AppDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
