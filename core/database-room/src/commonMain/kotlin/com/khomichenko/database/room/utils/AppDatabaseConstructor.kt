package com.khomichenko.database.room.utils

import androidx.room.RoomDatabaseConstructor
import com.khomichenko.database.room.database.AppDatabase

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor: RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}