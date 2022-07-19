package com.example.notesvsshoppinglist.core.model

import android.os.Parcelable
import com.rino.database.entity.ChecklistTask
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class ChecklistWithTask(
    var id: Long,
    var title: String,
    var description: String,
    var isDone: Boolean,
    var createdAt: Date = Date(),
    var listTask: List<ChecklistTask> = arrayListOf()
) : Parcelable