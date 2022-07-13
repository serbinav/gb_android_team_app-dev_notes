package com.example.notesvsshoppinglist.ui.checklist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ToDoData(
    var name: String = "Креветка",
    var isDone: Boolean = false,
) : Parcelable