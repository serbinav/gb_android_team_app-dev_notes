package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow

interface ChecklistRepository {
    fun getAllChecklists(): List<ChecklistWithTask>
    fun getAllChecklistsFlow(): Flow<List<ChecklistWithTask>>
    fun updateChecklist(checklist: Checklist)
    fun deleteChecklistById(checklistId: Long)
}