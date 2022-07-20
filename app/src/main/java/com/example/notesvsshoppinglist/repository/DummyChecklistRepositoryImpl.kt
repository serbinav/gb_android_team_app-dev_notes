package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.getDate
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DummyChecklistRepositoryImpl : ChecklistRepository {
    private val fakeChecklists = arrayListOf(
        ChecklistWithTask(
            1,
            "Тестируем",
            "Здесь могла быть ваша реклама",
            false,
            getDate(2022, 2, 20),
            arrayListOf(),
        ),
        ChecklistWithTask(
            2,
            "Нужный список",
            "",
            false,
            getDate(2022, 2, 24),
            arrayListOf(
                ChecklistTask(1, 2, "отвертка", false),
                ChecklistTask(2, 2, "клещи", true),
                ChecklistTask(3, 2, "рубанок", false),
            )
        ),
        ChecklistWithTask(
            3,
            "Свободный",
            "",
            false,
            getDate(2022, 7, 4),
            arrayListOf(
                ChecklistTask(1, 3,"утка", true),
                ChecklistTask(1, 3,"собака", true),
                ChecklistTask(1, 3,"тунец", true),
                ChecklistTask(1, 3,"калао", true),
                ChecklistTask(1, 3,"акула", true),
                ChecklistTask(1, 3,"гепард", true),
                ChecklistTask(1, 3,"волк",false),
                ChecklistTask(1, 3,"барсук", true),
                ChecklistTask(1, 3,"чайка", true),
                ChecklistTask(1, 3,"свинья", true),
            )
        ),
        ChecklistWithTask(
            4,
            "Новогодний ",
            "",
            false,
            getDate(2022, 12, 30),
            arrayListOf(
                ChecklistTask(1, 4,"калина",false),
                ChecklistTask(1, 4,"малина", true),
                ChecklistTask(1, 4,"вишня", true),
                ChecklistTask(1, 4,"крыжовник", true),
            )
        ),
    )

    override fun getAllChecklists(): List<ChecklistWithTask> = fakeChecklists

    override fun getAllChecklistsFlow(): Flow<List<ChecklistWithTask>> = flow { emit(fakeChecklists) }

    override fun updateChecklist(checklist: Checklist) {
        TODO("Not yet implemented")
    }

    override fun deleteChecklistById(checklistId: Long) {
        TODO("Not yet implemented")
    }
}