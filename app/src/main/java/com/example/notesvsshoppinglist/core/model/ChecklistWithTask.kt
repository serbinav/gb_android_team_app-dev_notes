package com.example.notesvsshoppinglist.core.model

import android.os.Parcelable
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChecklistWithTask(
    var checklist: Checklist = Checklist(id = 0L, title = "", description = "", false),
    var listTask: List<ChecklistTask> = arrayListOf()
) : Parcelable {

    fun countDoneTask(): Int {
        return if (!this.checklist.isDone) {
            var countCompletedTasks = 0
            this.listTask.forEach {
                if (it.isMarked) {
                    countCompletedTasks++
                }
            }
            countCompletedTasks
        } else this.listTask.size
    }
}