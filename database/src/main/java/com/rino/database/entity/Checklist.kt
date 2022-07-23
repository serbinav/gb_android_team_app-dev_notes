package com.rino.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Checklist(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "isDone")
    val isDone: Boolean,
    @ColumnInfo(name = "createdAt")
    val createdAt: Date = Date()
)