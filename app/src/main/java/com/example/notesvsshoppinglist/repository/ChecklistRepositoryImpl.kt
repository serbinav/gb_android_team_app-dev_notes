package com.example.notesvsshoppinglist.repository

import com.rino.database.dao.ChecklistGetDao
import com.rino.database.dao.ChecklistSetDao
import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow

class ChecklistRepositoryImpl(
    private val checklistGetDao: ChecklistGetDao,
    private val checklistSetDao: ChecklistSetDao
) : ChecklistRepository {

    override fun getAllChecklists(): List<Checklist> = checklistGetDao.getAllChecklists()

    override fun getAllChecklistsFlow(): Flow<List<Checklist>> = checklistGetDao.getAllChecklistsFlow()

    override fun updateChecklist(checklist: Checklist) = checklistSetDao.insertChecklist(checklist)

    override fun deleteChecklistById(checklistId: Long) = checklistSetDao.deleteChecklistId(checklistId)

}