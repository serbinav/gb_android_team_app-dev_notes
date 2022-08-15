package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.provider.StringProvider
import com.example.notesvsshoppinglist.repository.ChecklistRepository
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import com.rino.database.entity.ChecklistWithTasks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class EditChecklistViewModel(
    private val checklistRepository: ChecklistRepository,
    private val checklistId: Long,
    private val stringProvider: StringProvider
) : ViewModel() {
    private val _currentChecklist = MutableLiveData<ChecklistWithTasks>()
    val currentChecklist: LiveData<ChecklistWithTasks> = _currentChecklist

    init {
        viewModelScope.launch {
            _currentChecklist.value = withContext(Dispatchers.IO) {
                val checklist = checklistRepository.getChecklistById(checklistId)
                    ?: Checklist(id = 0L, title = "", description = "", false)

                val listTask = checklistRepository.getChecklistTaskById(checklistId)

                ChecklistWithTasks(checklist, listTask)
            }
        }
    }

    fun updateChecklist(name: String, description: String) {
        currentChecklist.value?.let { checklistWithTasks ->
            var updatedChecklist = checklistWithTasks.checklist
                .copy(title = name, description = description)

            val checklistId = runBlocking(Dispatchers.IO) {
                checklistRepository.updateChecklist(updatedChecklist)
            }

            updatedChecklist = updatedChecklist.copy(id = checklistId)

            val updatedTasks = checklistWithTasks.tasks.map { task ->
                val updatedTask = task.copy(checklistId = checklistId)
                val taskId = runBlocking(Dispatchers.IO) {
                    checklistRepository.updateChecklistTask(updatedTask)
                }
                updatedTask.copy(id = taskId)
            }

            _currentChecklist.value = ChecklistWithTasks(updatedChecklist, updatedTasks)
        }
    }

    fun deleteChecklist() {
        currentChecklist.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                checklistRepository.deleteChecklistById(it.checklist.id)
                checklistRepository.deleteChecklistTaskByChecklistId(it.checklist.id)
            }
            _currentChecklist.value = null
        }
    }

    fun updateNameDescription(name: String, description: String) {
        currentChecklist.value?.let { checklistWithTask ->
            var updatedChecklist = checklistWithTask.checklist
                .copy(title = name, description = description)

            val checklistId = runBlocking(Dispatchers.IO) {
                checklistRepository.updateChecklist(updatedChecklist)
            }

            updatedChecklist = updatedChecklist.copy(id = checklistId)

            _currentChecklist.value = checklistWithTask.copy(checklist = updatedChecklist)
        }
    }

    fun updateChecklistTask(checklistTask: ChecklistTask) {
        viewModelScope.launch {
            currentChecklist.value?.let { checklistWithTask ->
                val listTask = withContext(Dispatchers.IO) {
                    val updatedTask = checklistTask.copy(checklistId = checklistWithTask.checklist.id)
                    checklistRepository.updateChecklistTask(updatedTask)
                    checklistRepository.getChecklistTaskById(checklistWithTask.checklist.id)
                }
                _currentChecklist.value = checklistWithTask.copy(tasks = listTask)
            }
        }
    }

    fun deleteChecklistTask(checklistTaskId: Long) {
        viewModelScope.launch {
            currentChecklist.value?.let { checklistWithTask ->
                val listTask = withContext(Dispatchers.IO) {
                    checklistRepository.deleteChecklistTaskById(checklistTaskId)
                    checklistRepository.getChecklistTaskById(checklistId)
                }
                _currentChecklist.value = checklistWithTask.copy(tasks = listTask)
            }
        }
    }

}