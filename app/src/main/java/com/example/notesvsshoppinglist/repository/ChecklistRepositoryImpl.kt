package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.rino.database.dao.ChecklistGetDao
import com.rino.database.dao.ChecklistSetDao
import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChecklistRepositoryImpl(
    private val checklistGetDao: ChecklistGetDao,
    private val checklistSetDao: ChecklistSetDao
) : ChecklistRepository {

    override fun getAllChecklists(): List<ChecklistWithTask> {
        return checklistGetDao.getAllChecklists().map { checklist ->
            ChecklistWithTask(
                Checklist(
                    checklist.id,
                    checklist.title,
                    checklist.description,
                    checklist.isDone,
                    checklist.createdAt
                ),
                listTask = arrayListOf()
            )
        }
    }

    override fun getAllChecklistsFlow(): Flow<List<ChecklistWithTask>> {
        return checklistGetDao.getAllChecklistsFlow().map { list: List<Checklist> ->
            list.map {
                ChecklistWithTask(
                    Checklist(
                        it.id,
                        it.title,
                        it.description,
                        it.isDone,
                        it.createdAt
                    ),
                    listTask = arrayListOf()
                )
            }
        }
    }

    override fun getChecklistById(checklistId: Long): Checklist? =
        checklistGetDao.getChecklistById(checklistId)

    override fun updateChecklist(checklist: Checklist) = checklistSetDao.insertChecklist(checklist)

    override fun deleteChecklistById(checklistId: Long) =
        checklistSetDao.deleteChecklistId(checklistId)

}