package com.example.notesvsshoppinglist.ui.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditNotesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Under construction"
    }
    val text: LiveData<String> = _text
}