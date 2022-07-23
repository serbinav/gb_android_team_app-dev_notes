package com.rino.database.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity
data class ChecklistTask(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
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
): Parcelable