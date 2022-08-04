package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.provider.StringProvider
import com.example.notesvsshoppinglist.repository.ChecklistRepository
import com.rino.database.entity.Checklist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditChecklistViewModel(
    private val checklistRepository: ChecklistRepository,
    private val checklistId: Long,
    private val stringProvider: StringProvider
) : ViewModel() {
    private val _currentChecklist = MutableLiveData<ChecklistWithTask>()
    val currentChecklist: LiveData<ChecklistWithTask> = _currentChecklist

    init {
        viewModelScope.launch {
            _currentChecklist.value = withContext(Dispatchers.IO) {
                val checklist = checklistRepository.getChecklistById(checklistId) ?: Checklist(
                    id = 0L,
                    title = stringProvider.newNote,
                    description = "",
                    false
                )
                ChecklistWithTask(checklist)
            }
        }
    }

    fun deleteChecklist() {
        currentChecklist.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                checklistRepository.deleteChecklistById(it.checklist.id)
            }
            _currentChecklist.value = null
        }
    }
}