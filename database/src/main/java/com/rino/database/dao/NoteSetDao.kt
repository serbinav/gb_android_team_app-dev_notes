package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rino.database.entity.Note

@Dao
interface NoteSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note): Long

    @Query("DELETE FROM note WHERE id=:noteId")
    fun deleteNoteById(noteId: Long)

}