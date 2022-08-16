package com.rino.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rino.database.converter.DateConverter
import com.rino.database.dao.*
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import com.rino.database.entity.Note

@Database(
    entities = [
        Note::class, Checklist::class, ChecklistTask::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val noteGetDao: NoteGetDao
    abstract val noteSetDao: NoteSetDao

    abstract val checklistGetDao: ChecklistGetDao
    abstract val checklistSetDao: ChecklistSetDao

    abstract val checklistTaskDao: ChecklistTaskDao
}