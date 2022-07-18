package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithCounters
import com.rino.database.dao.ChecklistGetDao
import com.rino.database.dao.ChecklistSetDao
import com.rino.database.entity.Checklist
import com.example.notesvsshoppinglist.core.utils.toFormatString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ChecklistRepositoryImpl(
    private val checklistGetDao: ChecklistGetDao,
    private val checklistSetDao: ChecklistSetDao
) : ChecklistRepository {

    override fun getAllChecklists(): List<ChecklistWithCounters> {
        return checklistGetDao.getAllChecklists().map { checklist ->
            ChecklistWithCounters(
                date = checklist.createdAt.toFormatString(),
                name = checklist.title
            )
        }
    }

    override fun getAllChecklistsFlow(): Flow<List<ChecklistWithCounters>> {
        return checklistGetDao.getAllChecklistsFlow().map { list: List<Checklist> ->
            list.map {
                ChecklistWithCounters(
                    date = it.createdAt.toFormatString(),
                    name = it.title
                )
            }

        }
    }

    override fun updateChecklist(checklist: Checklist) = checklistSetDao.insertChecklist(checklist)

    override fun deleteChecklistById(checklistId: Long) =
        checklistSetDao.deleteChecklistId(checklistId)

}