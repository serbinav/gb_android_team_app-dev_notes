package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.core.model.ChecklistWithCounters
import com.example.notesvsshoppinglist.repository.ChecklistRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ChecklistViewModel(
    checklistRepository: ChecklistRepository
) : ViewModel() {

    private var mockData = arrayListOf(
        ChecklistData(),
        ChecklistData(
            NotesData(
                "24.02.2022",
                "Нужный список",
                ""
            ),
            arrayListOf(
                ToDoData("отвертка"),
                ToDoData("клещи", true),
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
                ToDoData("волк"),
                ToDoData("барсук", true),
                ToDoData("чайка", true),
                ToDoData("свинья", true),
            )
        ),
        ChecklistData(
            NotesData(
                "30.12.2022",
                "Новогодний ",
                ""
            ),
            arrayListOf(
                ToDoData("калина"),
                ToDoData("малина", true),
                ToDoData("вишня", true),
                ToDoData("крыжовник", true),
            )
        ),
    )

    private val _checklists = MutableLiveData<List<ChecklistWithCounters>>(listOf())
    val checklists: LiveData<List<ChecklistWithCounters>> = _checklists

    init {
        viewModelScope.launch {
            checklistRepository.getAllChecklistsFlow()
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    _checklists.value = it
                }
        }
    }

    private fun sortData(data: List<ChecklistData>): List<ChecklistData> {
        val sortedData = arrayListOf<ChecklistData>()
        data.forEach { checklist ->
            val (match, other) = checklist.listTasks.partition { !it.isDone }
            val concat = match.toCollection(arrayListOf())
            concat.addAll(other)
            sortedData.add(ChecklistData(checklist.notes, concat))
        }
        return sortedData
    }

}