package com.rino.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rino.database.entity.Checklist

@Dao
interface ChecklistSetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChecklist(checklist: Checklist)

}