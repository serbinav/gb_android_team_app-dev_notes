package com.example.notesvsshoppinglist.core.utils

import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import java.util.*

class Utils {
    companion object Utils {

        fun getDate(
            year: Int,
            month: Int,
            day: Int,
            hour: Int = 0,
            minute: Int = 0,
            second: Int = 0
        ): Date {
            val calendar = Calendar.getInstance().apply {
                set(year, month, day, hour, minute, second)
            }
            return Date(calendar.timeInMillis)
        }

        fun countDoneTask(checklist: ChecklistWithTask): Int {
            return if (!checklist.isDone) {
                var countCompletedTasks = 0
                checklist.listTask.forEach {
                    if (it.isMarked) {
                        countCompletedTasks++
                    }
                }
                countCompletedTasks
            } else checklist.listTask.size
        }
    }
}