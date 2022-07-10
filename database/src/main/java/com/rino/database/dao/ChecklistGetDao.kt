package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.rino.database.entity.Checklist

@Dao
interface ChecklistGetDao {

    @Query("SELECT * FROM Checklist WHERE NOT isDeleted ORDER BY createdAt DESC")
    fun getAllChecklists(): List<Checklist>

}