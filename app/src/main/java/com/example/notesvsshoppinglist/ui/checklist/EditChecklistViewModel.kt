package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.provider.StringProvider
import com.example.notesvsshoppinglist.repository.ChecklistRepository
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
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
                val listTask = checklistRepository.getChecklistTaskById(checklistId)
                    ?: arrayListOf()

                ChecklistWithTask(checklist, listTask)
            }
        }
    }

    fun updateChecklist(name: String, description: String, data: ArrayList<ChecklistTask>) {
        currentChecklist.value?.let { checklistWithTask ->
            val checklist = checklistWithTask.checklist.copy(
                title = name,
                description = description
            )
            val checklistId = runBlocking(Dispatchers.IO) {
                checklistRepository.updateChecklist(checklist)
            }
            val listTask = arrayListOf<ChecklistTask>()
            data.forEach { checklistTask ->
                val task = checklistTask.copy(checklistId = checklistId)
                    viewModelScope.launch(Dispatchers.IO) {
                        checklistRepository.updateChecklistTask(task)
                    }
                listTask.add(task)
            }
            _currentChecklist.value = ChecklistWithTask(checklist, listTask)
        }
    }

    fun deleteChecklist() {
        currentChecklist.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                checklistRepository.deleteChecklistById(it.checklist.id)
                checklistRepository.deleteChecklistTaskId(it.checklist.id)
            }
            _currentChecklist.value = null
        }
    }
}