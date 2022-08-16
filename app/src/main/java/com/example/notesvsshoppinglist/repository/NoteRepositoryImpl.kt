package com.example.notesvsshoppinglist.repository

import com.rino.database.dao.NoteGetDao
import com.rino.database.dao.NoteSetDao
import com.rino.database.entity.Note
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteGetDao: NoteGetDao,
    private val noteSetDao: NoteSetDao
) : NoteRepository {

    override fun getAllNotes(): List<Note> = noteGetDao.getAllNotes()

    override fun getAllNotesFlow(): Flow<List<Note>> = noteGetDao.getAllNotesFlow()

    override fun getNoteById(noteId: Long): Note? = noteGetDao.getNoteById(noteId)

    override fun updateNote(note: Note):Long = noteSetDao.insertNote(note)

    override fun deleteNoteById(noteId: Long) = noteSetDao.deleteNoteById(noteId)

}