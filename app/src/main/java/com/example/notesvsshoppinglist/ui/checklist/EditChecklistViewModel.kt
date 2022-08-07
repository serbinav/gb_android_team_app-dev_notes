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
                val checklist = checklistRepository.getChecklistById(checklistId) ?: Checklist(
                    id = 0L,
                    title = stringProvider.newNote,
                    description = "",
                    false
                )
                val listTask = checklistRepository.getChecklistTaskById(checklistId)
                    ?: arrayListOf()

                ChecklistWithTasks(checklist, listTask)
            }
        }
    }

    fun updateChecklist(name: String, description: String) {
        currentChecklist.value?.let { checklistWithTask ->
            val checklist = checklistWithTask.checklist.copy(
                title = name,
                description = description
            )
            val checklistId = runBlocking(Dispatchers.IO) {
                checklistRepository.updateChecklist(checklist)
            }

            if (checklistWithTask.tasks.isNotEmpty() && checklistWithTask.tasks.first().checklistId == 0L) {
                val listTask = arrayListOf<ChecklistTask>()
                runBlocking(Dispatchers.IO) {
                    checklistWithTask.tasks.forEach { checklistTask ->
                        val task = ChecklistTask(
                            id = checklistTask.id,
                            checklistId = checklistId,
                            title = checklistTask.title,
                            isMarked = checklistTask.isMarked,
                            amount = checklistTask.amount,
                            createdAt = checklistTask.createdAt,
                        )
                        viewModelScope.launch(Dispatchers.IO) {
                            checklistRepository.updateChecklistTask(task)
                        }
                        listTask.add(task)
                    }
                }
                _currentChecklist.value = ChecklistWithTasks(checklist, listTask)
            }
            _currentChecklist.value = ChecklistWithTasks(checklist, checklistWithTask.tasks)
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
            val checklist = checklistWithTask.checklist.copy(
                title = name,
                description = description
            )
            runBlocking(Dispatchers.IO) {
                checklistRepository.updateChecklist(checklist)
            }
            _currentChecklist.value = ChecklistWithTasks(checklist, checklistWithTask.tasks)
        }
    }

    fun updateChecklistTask(checklistTask: ChecklistTask) {
        currentChecklist.value?.let { checklistWithTask ->
            val checklistTaskId = runBlocking(Dispatchers.IO) {
                checklistRepository.updateChecklistTask(checklistTask)
            }
            val listTask = checklistWithTask.tasks.toMutableList()

            listTask.find {
                it.id == checklistTaskId
            }?.let {
                listTask[listTask.indexOf(it)] =
                    ChecklistTask(
                        id = checklistTaskId,
                        checklistId = checklistTask.checklistId,
                        title = checklistTask.title,
                        isMarked = checklistTask.isMarked,
                        amount = checklistTask.amount,
                        createdAt = checklistTask.createdAt,
                    )
            } ?: listTask.add(checklistTask)

            _currentChecklist.value = ChecklistWithTasks(checklistWithTask.checklist, listTask)
        }
    }

    fun deleteChecklistTask(checklistTaskId: Long) {
        currentChecklist.value?.let { checklistWithTask ->
            viewModelScope.launch(Dispatchers.IO) {
                checklistRepository.deleteChecklistTaskById(checklistTaskId)
            }
            val listTask = checklistWithTask.tasks.toMutableList()
            listTask.find {
                it.id == checklistTaskId
            }?.let {
                listTask.remove(it)
            }
            _currentChecklist.value = ChecklistWithTasks(checklistWithTask.checklist, listTask)
        }
    }
}