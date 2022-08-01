package com.example.notesvsshoppinglist.repository

import com.rino.database.entity.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getAllNotes(): List<Note>
    fun getAllNotesFlow(): Flow<List<Note>>
    fun getNoteById(noteId: Long): Note?
    fun updateNote(note: Note): Long
    fun deleteNoteById(noteId: Long)
}