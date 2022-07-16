package com.example.notesvsshoppinglist.repository

import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow

interface ChecklistRepository {
    fun getAllChecklists(): List<Checklist>
    fun getAllChecklistsFlow(): Flow<List<Checklist>>
    fun updateChecklist(checklist: Checklist)
    fun deleteChecklistById(checklistId: Long)
}