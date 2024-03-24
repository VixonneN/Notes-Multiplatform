package com.khomichenko.database.driver

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.khomichenko.database.db.notesCacheDatabase

internal actual class DatabaseDriverFactory {
    actual fun createDriver() : SqlDriver =
        NativeSqliteDriver(notesCacheDatabase.Schema, DATABASE_NAME)
    
    companion object {
        const val DATABASE_NAME = "cache.db"
    }
}