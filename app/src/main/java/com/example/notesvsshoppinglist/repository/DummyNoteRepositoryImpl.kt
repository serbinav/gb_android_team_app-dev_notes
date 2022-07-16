package com.example.notesvsshoppinglist.repository

import com.rino.database.entity.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class DummyNoteRepositoryImpl : NoteRepository {
    private val fakeNotes = listOf(
        Note(
            1,
            "Тестируем",
            "Здесь могла быть ваша реклама",
            Date(122, 2, 20)
        ),
        Note(
            2,
            "Вторая заметка",
            "",
            Date(122, 7, 5)
        ),
        Note(
            3,
            "Между первой и второй, перерывчик небольшой",
            "Третья заметка",
            Date(122, 9, 30),
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