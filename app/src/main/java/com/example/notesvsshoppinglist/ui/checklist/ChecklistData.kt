package com.example.notesvsshoppinglist.ui.checklist

import com.example.notesvsshoppinglist.ui.notes.NotesData

data class ChecklistData(
    var notes: NotesData = NotesData(),
    var listTasks: List<ToDo> = arrayListOf()
) {
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