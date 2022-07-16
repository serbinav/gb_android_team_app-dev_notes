package com.example.notesvsshoppinglist.core.model

data class ChecklistWithCounters (
    var date: String = "10.01.2022",
    var name: String = "Список 1",
    var currentNumberCompletedTasks: Int = 10,
    var totalTasksCount: Int = 20
)