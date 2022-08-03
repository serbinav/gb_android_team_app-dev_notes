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
                    id = checklist.id,
                    title = checklist.title,
                    description = checklist.description,
                    isDone = checklist.isDone,
                    createdAt = checklist.createdAt
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
                        id = it.id,
                        title = it.title,
                        description = it.description,
                        isDone = it.isDone,
                        createdAt = it.createdAt
                    ),
                    listTask = arrayListOf()
                )
            }
        }
    }

    override fun getChecklistById(checklistId: Long): Checklist? {
        TODO("Not yet implemented")
    }

    override fun updateChecklist(checklist: Checklist) = checklistSetDao.insertChecklist(checklist)

    override fun deleteChecklistById(checklistId: Long) =
        checklistSetDao.deleteChecklistId(checklistId)

}