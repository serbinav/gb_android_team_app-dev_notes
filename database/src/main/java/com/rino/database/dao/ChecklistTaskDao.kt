package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rino.database.entity.ChecklistTask

@Dao
interface ChecklistTaskDao {

    @Query("SELECT * FROM ChecklistTask WHERE checklistId = :checklistId")
    fun getChecklistTaskById(checklistId: Long): List<ChecklistTask>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChecklistTask(checklistTask: ChecklistTask): Long

    @Query("DELETE FROM ChecklistTask WHERE checklistId=:checklistId")
    fun deleteChecklistTaskId(checklistId: Long)

    @Query("DELETE FROM ChecklistTask WHERE title=:title")
    fun deleteChecklistTaskTitle(title: String)

}