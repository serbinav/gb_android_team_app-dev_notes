package com.rino.database

import android.content.Context
import androidx.room.Room

object DatabaseModule {
    private const val DB_NAME = "lists_and_notes.db"

    fun getAppDatabase(context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .build()

    fun getNoteGetDao(database: AppDatabase) = database.noteGetDao
    fun getNoteSetDao(database: AppDatabase) = database.noteSetDao

    fun getChecklistGetDao(database: AppDatabase) = database.checklistGetDao
    fun getChecklistSetDao(database: AppDatabase) = database.checklistSetDao

    fun getChecklistTaskDao(database: AppDatabase) = database.checklistTaskDao
}