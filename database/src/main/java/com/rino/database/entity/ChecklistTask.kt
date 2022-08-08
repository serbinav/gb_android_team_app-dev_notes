package com.rino.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ChecklistTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "checklistId")
    val checklistId: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "isMarked")
    val isMarked: Boolean,
    @ColumnInfo(name = "amount")
    val amount: Long = 0L,
    @ColumnInfo(name = "createdAt")
    val createdAt: Date = Date()
)