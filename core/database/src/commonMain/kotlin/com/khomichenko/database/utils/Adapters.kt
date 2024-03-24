package com.khomichenko.database.utils

import app.cash.sqldelight.ColumnAdapter

internal val intLongColumnAdapter = object : ColumnAdapter<Int, Long> {
    override fun decode(databaseValue: Long): Int {
        return databaseValue.toInt()
    }

    override fun encode(value: Int): Long {
        return value.toLong()
    }
}

internal val stringColumnAdapter = object : ColumnAdapter<String, String> {
    override fun decode(databaseValue: String): String {
        return databaseValue
    }

    override fun encode(value: String): String {
        return value
    }

}
