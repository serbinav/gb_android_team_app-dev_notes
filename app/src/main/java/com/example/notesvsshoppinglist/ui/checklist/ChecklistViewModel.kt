package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.repository.ChecklistRepository
import com.rino.database.entity.Checklist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ChecklistViewModel(
    checklistRepository: ChecklistRepository
) : ViewModel() {

    private val _checklists = MutableLiveData<List<ChecklistWithTask>>(listOf())
    val checklists: LiveData<List<ChecklistWithTask>> = _checklists

    init {
        viewModelScope.launch {
            checklistRepository.getAllChecklistsFlow()
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    _checklists.value = sortData(it)
                }
        }
    }

    private fun sortData(data: List<ChecklistWithTask>): List<ChecklistWithTask> {
        val sortedData = arrayListOf<ChecklistWithTask>()
        data.forEach { checklistWithTask ->
            val (match, other) = checklistWithTask.listTask.partition { !it.isMarked }
            val concat = match.toCollection(arrayListOf())
            concat.addAll(other)
            sortedData.add(
                ChecklistWithTask(
                    Checklist(
                        checklistWithTask.checklist.id,
                        checklistWithTask.checklist.title,
                        checklistWithTask.checklist.description,
                        checklistWithTask.checklist.isDone,
                        checklistWithTask.checklist.createdAt
                    ),
                    concat
                )
            )
        }
        return sortedData
    }

}