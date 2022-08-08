package com.example.notesvsshoppinglist.repository

import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import com.rino.database.entity.ChecklistWithTasks
import kotlinx.coroutines.flow.Flow

interface ChecklistRepository {
    fun getAllChecklists(): List<ChecklistWithTasks>
    fun getAllChecklistsWithTasksFlow(): Flow<List<ChecklistWithTasks>>
    fun getChecklistById(checklistId: Long): Checklist?
    fun updateChecklist(checklist: Checklist): Long
    fun deleteChecklistById(checklistId: Long)
    fun getChecklistTaskById(checklistId: Long): List<ChecklistTask>
    fun updateChecklistTask(checklistTask: ChecklistTask): Long
    fun deleteChecklistTaskByChecklistId(checklistId: Long)
    fun deleteChecklistTaskById(checklistTaskId: Long)

}