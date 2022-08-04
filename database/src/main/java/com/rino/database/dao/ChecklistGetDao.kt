package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow

@Dao
interface ChecklistGetDao {

    @Query("SELECT * FROM Checklist ORDER BY createdAt DESC")
    fun getAllChecklists(): List<Checklist>

    @Query("SELECT * FROM Checklist ORDER BY createdAt DESC")
    fun getAllChecklistsFlow(): Flow<List<Checklist>>

    @Query("SELECT * FROM Checklist WHERE id = :checklistId")
    fun getChecklistById(checklistId: Long): Checklist?

}