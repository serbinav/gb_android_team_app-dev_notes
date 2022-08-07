package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.getDate
import com.rino.database.entity.Checklist
import com.rino.database.entity.ChecklistTask
import com.rino.database.entity.ChecklistWithTasks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class DummyChecklistRepositoryImpl : ChecklistRepository {
    private val fakeChecklists = arrayListOf(
        ChecklistWithTasks(
            Checklist(
                1,
                "Здесь могла быть ваша реклама, очень очень много вашей рекламы и можно даже еще немного больше",
                "Тестируем",
                false,
                getDate(2022, 2, 20)
            ),
            arrayListOf(),
        ),
        ChecklistWithTasks(
            Checklist(
                2,
                "Нужный список",
                "",
                false,
                getDate(2022, 2, 24)
            ),
            arrayListOf(
                ChecklistTask(1, 2, "отвертка", false),
                ChecklistTask(2, 2, "клещи", true),
                ChecklistTask(3, 2, "рубанок", false),
            )
        ),
        ChecklistWithTasks(
            Checklist(
                3,
                "Свободный",
                "",
                false,
                getDate(2022, 7, 4)
            ),
            arrayListOf(
                ChecklistTask(1, 3, "утка", true),
                ChecklistTask(2, 3, "собака", true),
                ChecklistTask(3, 3, "тунец", true),
                ChecklistTask(4, 3, "калао", true),
                ChecklistTask(5, 3, "акула", true),
                ChecklistTask(6, 3, "гепард", true),
                ChecklistTask(7, 3, "волк", false),
                ChecklistTask(8, 3, "барсук", true),
                ChecklistTask(9, 3, "чайка", true),
                ChecklistTask(10, 3, "свинья", true),
            )
        ),
        ChecklistWithTasks(
            Checklist(
                4,
                "Новогодний ",
                "",
                false,
                getDate(2022, 12, 30)
            ),
            arrayListOf(
                ChecklistTask(1, 4, "калина", false),
                ChecklistTask(2, 4, "малина", true),
                ChecklistTask(3, 4, "вишня", true),
                ChecklistTask(4, 4, "крыжовник", true),
            )
        ),
    )

    override fun getAllChecklists(): List<ChecklistWithTasks> = fakeChecklists

    override fun getAllChecklistsWithTasksFlow(): Flow<ArrayList<ChecklistWithTasks>> =
        flow { emit(fakeChecklists) }

    override fun getChecklistById(checklistId: Long): Checklist? {
        TODO("Not yet implemented")
    }

    override fun updateChecklist(checklist: Checklist): Long {
        TODO("Not yet implemented")
    }

    override fun deleteChecklistById(checklistId: Long) {
        TODO("Not yet implemented")
    }

    override fun getChecklistTaskById(checklistId: Long): List<ChecklistTask>? {
        TODO("Not yet implemented")
    }

    override fun updateChecklistTask(checklistTask: ChecklistTask): Long {
        TODO("Not yet implemented")
    }

    override fun deleteChecklistTaskByChecklistId(checklistId: Long) {
        TODO("Not yet implemented")
    }

    override fun deleteChecklistTaskById(checklistTaskId: Long) {
        TODO("Not yet implemented")
    }

}