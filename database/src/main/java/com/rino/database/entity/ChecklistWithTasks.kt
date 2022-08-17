package com.rino.database.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ChecklistWithTasks(
    @Embedded val checklist: Checklist,
    @Relation(parentColumn = "id", entityColumn = "checklistId")
    val tasks: List<ChecklistTask>
) {

    val numberOfCompletedTasks: Int
        get() = tasks.count { task -> task.isMarked }

}