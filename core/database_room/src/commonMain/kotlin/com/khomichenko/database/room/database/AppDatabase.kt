package com.khomichenko.database.room.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.khomichenko.database.room.dao.NoteDao
import com.khomichenko.database.room.entity.NoteEntity
import com.khomichenko.database.room.utils.AppDatabaseConstructor

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): NoteDao
}
