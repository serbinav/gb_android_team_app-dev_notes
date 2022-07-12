package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.rino.database.entity.Note

@Dao
interface NoteGetDao {

    @Query("SELECT * FROM Note WHERE NOT isDeleted ORDER BY createdAt DESC")
    fun getAllNotes(): List<Note>

}