package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rino.database.entity.Note

@Dao
interface NoteSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(word: Note)

}