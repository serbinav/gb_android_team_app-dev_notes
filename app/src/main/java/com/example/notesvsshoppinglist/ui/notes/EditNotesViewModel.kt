package com.example.notesvsshoppinglist.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.repository.NoteRepository
import com.rino.database.entity.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class EditNotesViewModel(
    private val noteRepository: NoteRepository,
    private val noteId: Long
) : ViewModel() {
    private val _currentNote = MutableLiveData<Note>()
    val currentNote: LiveData<Note> = _currentNote

    init {
        viewModelScope.launch {
            _currentNote.value = withContext(Dispatchers.IO) {
                noteRepository.getNoteById(noteId) ?: Note(
                    title = "Новая заметка",
                    description = ""
                )
            }
        }
    }

    fun updateNote(name: String, description: String) {
        currentNote.value?.let { note ->
            val updatedNote = note.copy(title = name, description = description)
            val noteId = runBlocking(Dispatchers.IO) {
                noteRepository.updateNote(updatedNote)
            }

            _currentNote.value = updatedNote.copy(id = noteId)
        }
    }

    fun deleteNote() {
        currentNote.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                noteRepository.deleteNoteById(it.id)
            }
            _currentNote.value = null
        }
    }

}