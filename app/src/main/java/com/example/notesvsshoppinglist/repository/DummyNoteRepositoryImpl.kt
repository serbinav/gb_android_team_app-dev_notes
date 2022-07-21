package com.example.notesvsshoppinglist.repository

import com.example.notesvsshoppinglist.core.utils.getDate
import com.rino.database.entity.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DummyNoteRepositoryImpl : NoteRepository {
    private val fakeNotes = listOf(
        Note(
            1,
            "Тестируем",
            "Здесь могла быть ваша реклама, очень очень много вашей рекламы и можно даже еще немного больше",
            getDate(2022, 2, 20)
        ),
        Note(
            2,
            "Вторая заметка",
            "",
            getDate(2022, 7, 5)
        ),
        Note(
            3,
            "Между первой и второй перерывчик небольшой, От весёлой пляски отлетают каблуки. После пятой и шестой как поеду я домой? Ой давай, давай, давай, танцуем, земляки!",
            "Третья заметка",
            getDate(2022, 9, 30)
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
