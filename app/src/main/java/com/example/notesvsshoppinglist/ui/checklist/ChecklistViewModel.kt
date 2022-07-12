package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesvsshoppinglist.ui.notes.NotesData

class ChecklistViewModel : ViewModel() {
    private var mockData = arrayListOf(
        ChecklistData(),
        ChecklistData(
            NotesData(
                "24.02.2022",
                "Нужный список",
                ""
            ),
            arrayListOf(
                ToDo("отвертка", true),
                ToDo("клещи"),
                ToDo("рубанок"),
            )
        ),
        ChecklistData(
            NotesData(
                "4.07.2022",
                "Свободный",
                ""
            ),
            arrayListOf(
                ToDo("утка", true),
                ToDo("собака", true),
                ToDo("тунец", true),
                ToDo("калао", true),
                ToDo("акула", true),
                ToDo("гепард", true),
                ToDo("волк", true),
                ToDo("барсук", true),
                ToDo("чайка", true),
                ToDo("свинья"),
            )
        ),
        ChecklistData(
            NotesData(
                "30.12.2022",
                "Новогодний ",
                ""
            ),
            arrayListOf(
                ToDo("калина", true),
                ToDo("малина", true),
                ToDo("вишня", true),
                ToDo("крыжовник"),
            )
        ),
    )

    private val _text = MutableLiveData<List<ChecklistData>>().apply {
        value = mockData
    }
    val text: LiveData<List<ChecklistData>> = _text
}