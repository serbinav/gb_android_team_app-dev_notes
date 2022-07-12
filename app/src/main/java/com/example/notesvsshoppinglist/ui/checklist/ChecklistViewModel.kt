package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChecklistViewModel : ViewModel() {
    private var mockData = arrayListOf(
        ChecklistData(),
        ChecklistData(
            "24.02.2022",
            "Нужный список",
            5,
            15
        ),
        ChecklistData(
            "4.07.2022",
            "Свободный",
            99,
            100
        ),
        ChecklistData(
            "30.12.2022",
            "Новогодний ",
            3,
            4
        ),
    )

    private val _text = MutableLiveData<List<ChecklistData>>().apply {
        value = mockData
    }
    val text: LiveData<List<ChecklistData>> = _text
}