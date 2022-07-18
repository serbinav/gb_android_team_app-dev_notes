package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithCounters
import com.rino.database.entity.Checklist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DummyChecklistRepositoryImpl : ChecklistRepository {
    private val fakeChecklists = listOf(
        ChecklistWithCounters(),
        ChecklistWithCounters(
            "24.02.2022",
            "Нужный список",
            5,
            15
        ),
        ChecklistWithCounters(
            "4.07.2022",
            "Свободный",
            99,
            100
        ),
        ChecklistWithCounters(
            "30.12.2022",
            "Новогодний ",
            3,
            4
        ),
    )
    override fun getAllChecklists(): List<ChecklistWithCounters> = fakeChecklists

    override fun getAllChecklistsFlow(): Flow<List<ChecklistWithCounters>> = flow { emit(fakeChecklists) }

    override fun updateChecklist(checklist: Checklist) {
        TODO("Not yet implemented")
    }

    override fun deleteChecklistById(checklistId: Long) {
        TODO("Not yet implemented")
    }
}