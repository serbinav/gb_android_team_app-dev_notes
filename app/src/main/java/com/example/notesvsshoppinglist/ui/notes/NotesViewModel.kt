package com.example.notesvsshoppinglist.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesvsshoppinglist.repository.NoteRepository
import com.rino.database.entity.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class NotesViewModel(
    noteRepository: NoteRepository
) : ViewModel() {

    private val _notes = MutableLiveData<List<Note>>(listOf())
    val notes: LiveData<List<Note>> = _notes

    init {
        viewModelScope.launch {
            noteRepository.getAllNotesFlow()
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    _notes.value = it
                }
        }
    }
}