@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package com.khomichenko.database.room.utils

import androidx.room.RoomDatabaseConstructor
import com.khomichenko.database.room.database.AppDatabase

expect object AppDatabaseConstructor: RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}