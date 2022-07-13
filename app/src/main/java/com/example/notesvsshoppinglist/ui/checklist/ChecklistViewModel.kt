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
                ToDoData("отвертка", true),
                ToDoData("клещи"),
                ToDoData("рубанок"),
            )
        ),
        ChecklistData(
            NotesData(
                "4.07.2022",
                "Свободный",
                ""
            ),
            arrayListOf(
                ToDoData("утка", true),
                ToDoData("собака", true),
                ToDoData("тунец", true),
                ToDoData("калао", true),
                ToDoData("акула", true),
                ToDoData("гепард", true),
                ToDoData("волк", true),
                ToDoData("барсук", true),
                ToDoData("чайка", true),
                ToDoData("свинья"),
            )
        ),
        ChecklistData(
            NotesData(
                "30.12.2022",
                "Новогодний ",
                ""
            ),
            arrayListOf(
                ToDoData("калина", true),
                ToDoData("малина", true),
                ToDoData("вишня", true),
                ToDoData("крыжовник"),
            )
        ),
    )

    private val _text = MutableLiveData<List<ChecklistData>>().apply {
        value = mockData
    }
    val text: LiveData<List<ChecklistData>> = _text
}