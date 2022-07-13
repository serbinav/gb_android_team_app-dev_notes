package com.example.notesvsshoppinglist.ui.notes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NotesData (
    var date: String = "20.02.2022",
    var name: String = "Тестируем",
    var description: String = "Здесь могла быть ваша реклама"
) : Parcelable