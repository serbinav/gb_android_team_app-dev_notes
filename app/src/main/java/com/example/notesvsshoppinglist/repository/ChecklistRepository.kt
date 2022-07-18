package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithCounters
import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow

interface ChecklistRepository {
    fun getAllChecklists(): List<ChecklistWithCounters>
    fun getAllChecklistsFlow(): Flow<List<ChecklistWithCounters>>
    fun updateChecklist(checklist: Checklist)
    fun deleteChecklistById(checklistId: Long)
}