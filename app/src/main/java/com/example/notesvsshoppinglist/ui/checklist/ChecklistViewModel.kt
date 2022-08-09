package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.repository.ChecklistRepository
import com.rino.database.entity.ChecklistWithTasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class ChecklistViewModel(
    checklistRepository: ChecklistRepository
) : ViewModel() {

    private val _checklists = MutableLiveData<List<ChecklistWithTasks>>(listOf())
    val checklists: LiveData<List<ChecklistWithTasks>> = _checklists

    init {
        viewModelScope.launch {
            checklistRepository.getAllChecklistsWithTasksFlow()
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    _checklists.value = it
                }
        }
    }

}