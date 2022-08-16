package com.rino.database.dao

import androidx.room.*
import com.rino.database.entity.Checklist

@Dao
interface ChecklistSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChecklist(checklist: Checklist): Long

    @Query("DELETE FROM Checklist WHERE id=:checklistId")
    fun deleteChecklistId(checklistId: Long)

}