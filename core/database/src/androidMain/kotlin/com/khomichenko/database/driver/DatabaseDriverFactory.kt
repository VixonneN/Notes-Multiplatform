package com.khomichenko.database.driver

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.khomichenko.database.db.notesCacheDatabase

internal actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver() : SqlDriver =
        AndroidSqliteDriver(notesCacheDatabase.Schema, context, DATABASE_NAME)

    companion object {
        private const val DATABASE_NAME = "cache.db"
    }
}