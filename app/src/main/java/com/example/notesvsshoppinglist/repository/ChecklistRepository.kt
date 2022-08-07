package com.example.notesvsshoppinglist.repository

import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import kotlinx.coroutines.flow.Flow

interface ChecklistRepository {
    fun getAllChecklists(): List<ChecklistWithTask>
    fun getAllChecklistsFlow(): Flow<List<ChecklistWithTask>>
    fun getChecklistById(checklistId: Long): Checklist?
    fun updateChecklist(checklist: Checklist): Long
    fun deleteChecklistById(checklistId: Long)
    fun getChecklistTaskById(checklistId: Long): List<ChecklistTask>?
    fun updateChecklistTask(checklistTask: ChecklistTask): Long
    fun deleteChecklistTaskByChecklistId(checklistId: Long)
    fun deleteChecklistTaskById(checklistTaskId: Long)

}