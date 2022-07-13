package com.example.notesvsshoppinglist.ui.checklist

import android.os.Parcelable
import com.example.notesvsshoppinglist.ui.notes.NotesData
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChecklistData(
    var notes: NotesData = NotesData(),
    var listTasks: List<ToDoData> = arrayListOf()
) : Parcelable {

    fun countDoneTask(): Int {
        var countCompletedTasks = 0
        this.listTasks.forEach {
            if (it.isDone) {
                countCompletedTasks++
            }
        }
        return countCompletedTasks
    }
}