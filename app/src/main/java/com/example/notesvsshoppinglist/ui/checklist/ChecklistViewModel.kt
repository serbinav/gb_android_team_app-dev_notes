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
}