package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.rino.database.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteGetDao {

    @Query("SELECT * FROM Note ORDER BY createdAt DESC")
    fun getAllNotes(): List<Note>

    @Query("SELECT * FROM Note ORDER BY createdAt DESC")
    fun getAllNotesFlow(): Flow<List<Note>>
}