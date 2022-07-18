package com.example.notesvsshoppinglist.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toFormatString(
    format: String = "dd.MM.yyyy",
    locale: Locale = Locale.getDefault()
): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}