package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.utils.Utils
import com.rino.database.entity.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DummyNoteRepositoryImpl : NoteRepository {
    private val fakeNotes = listOf(
        Note(
            1,
            "Тестируем",
            "Здесь могла быть ваша реклама",
            Utils.getDate(2022, 2, 20)
        ),
        Note(
            2,
            "Вторая заметка",
            "",
            Utils.getDate(2022, 7, 5)
        ),
        Note(
            3,
            "Между первой и второй, перерывчик небольшой",
            "Третья заметка",
            Utils.getDate(2022, 9, 30)
        ),
    )

    override fun getAllNotes(): List<Note> = fakeNotes

    override fun getAllNotesFlow(): Flow<List<Note>> = flow { emit(fakeNotes) }

    override fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun deleteNoteById(noteId: Long) {
        TODO("Not yet implemented")
    }
}
