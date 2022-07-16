package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithCounters
import com.rino.database.dao.ChecklistGetDao
import com.rino.database.dao.ChecklistSetDao
import com.rino.database.entity.Checklist
import com.rino.translator.core.toString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class ChecklistRepositoryImpl(
    private val checklistGetDao: ChecklistGetDao,
    private val checklistSetDao: ChecklistSetDao
) : ChecklistRepository {

    override fun getAllChecklists(): List<ChecklistWithCounters> {
        return checklistGetDao.getAllChecklists().map { checklist ->
            ChecklistWithCounters(
                date = checklist.createdAt.toString("dd.MM.yyyy"),
                name = checklist.title
            )
        }
    }

    override fun getAllChecklistsFlow(): Flow<List<ChecklistWithCounters>> {
        return checklistGetDao.getAllChecklistsFlow().map { list: List<Checklist> ->
            list.map {
                ChecklistWithCounters(
                    date = it.createdAt.toString("dd.MM.yyyy"),
                    name = it.title
                )
            }

        }
    }

    override fun updateChecklist(checklist: Checklist) = checklistSetDao.insertChecklist(checklist)

    override fun deleteChecklistById(checklistId: Long) =
        checklistSetDao.deleteChecklistId(checklistId)

}