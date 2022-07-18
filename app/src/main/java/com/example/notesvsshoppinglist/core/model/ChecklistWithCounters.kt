package com.example.notesvsshoppinglist.core.model

@Parcelize
data class ChecklistWithCounters (
    var date: String = "10.01.2022",
    var name: String = "Список 1",
    var currentNumberCompletedTasks: Int = 10,
    var totalTasksCount: Int = 20
): Parcelable {

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