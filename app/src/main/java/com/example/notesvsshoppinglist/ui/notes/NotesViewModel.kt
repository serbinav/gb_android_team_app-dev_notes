package com.example.notesvsshoppinglist.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotesViewModel : ViewModel() {
    private var mockData = arrayListOf(
        NotesData(),
        NotesData(
            "05.07.2022",
            "Вторая заметка",
            ""
        ),
        NotesData(
            "30.09.2022",
            "Третья заметка",
            "Между первой и второй, перерывчик небольшой"
        ),
    )

    private val _text = MutableLiveData<List<NotesData>> ().apply {
        value = mockData
    }
    val text: LiveData<List<NotesData>> = _text
}