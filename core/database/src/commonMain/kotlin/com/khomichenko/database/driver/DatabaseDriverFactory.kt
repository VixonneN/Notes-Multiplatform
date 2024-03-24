package com.khomichenko.database.driver

import app.cash.sqldelight.db.SqlDriver

internal expect class DatabaseDriverFactory {
    fun createDriver() : SqlDriver
}