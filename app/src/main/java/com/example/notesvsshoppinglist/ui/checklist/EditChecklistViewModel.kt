package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EditChecklistViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Under construction"
    }
    val text: LiveData<String> = _text
}