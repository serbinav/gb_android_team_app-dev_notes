package com.example.notesvsshoppinglist.repository

import com.rino.database.dao.ChecklistGetDao
import com.rino.database.dao.ChecklistSetDao
import com.rino.database.dao.ChecklistTaskDao
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import com.rino.database.entity.ChecklistWithTasks
import kotlinx.coroutines.flow.Flow

class ChecklistRepositoryImpl(
    private val checklistGetDao: ChecklistGetDao,
    private val checklistSetDao: ChecklistSetDao,
    private val checklistTaskDao: ChecklistTaskDao
) : ChecklistRepository {

    override fun getAllChecklists(): List<ChecklistWithTasks> = checklistGetDao.getAllChecklists()

    override fun getAllChecklistsWithTasksFlow(): Flow<List<ChecklistWithTasks>> =
        checklistGetDao.getAllChecklistsWithTasksFlow()

    override fun getChecklistById(checklistId: Long): Checklist? =
        checklistGetDao.getChecklistById(checklistId)

    override fun updateChecklist(checklist: Checklist) =
        checklistSetDao.insertChecklist(checklist)

    override fun deleteChecklistById(checklistId: Long) =
        checklistSetDao.deleteChecklistId(checklistId)

    override fun getChecklistTaskById(checklistId: Long): List<ChecklistTask> =
        checklistTaskDao.getChecklistTaskById(checklistId)

    override fun updateChecklistTask(checklistTask: ChecklistTask) =
        checklistTaskDao.insertChecklistTask(checklistTask)

    override fun deleteChecklistTaskByChecklistId(checklistId: Long) =
        checklistTaskDao.deleteChecklistTaskByChecklistId(checklistId)

    override fun deleteChecklistTaskById(checklistTaskId: Long) =
        checklistTaskDao.deleteChecklistTaskById(checklistTaskId)

}