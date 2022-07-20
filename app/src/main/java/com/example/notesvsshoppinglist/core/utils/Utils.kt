package com.example.notesvsshoppinglist.core.utils

import java.util.*

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