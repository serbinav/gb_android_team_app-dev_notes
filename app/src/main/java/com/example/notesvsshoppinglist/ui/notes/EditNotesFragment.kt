package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.View
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.FragmentEditNotesBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditNotesFragment :
    BaseFragment<FragmentEditNotesBinding>(FragmentEditNotesBinding::inflate) {

    companion object {
        const val NOTE_ID = "NOTE_ID"
    }

    private val editNotesViewModel: EditNotesViewModel by viewModel {
        parametersOf(arguments?.getLong(NOTE_ID) ?: -1L)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editNotesViewModel.currentNote.observe(viewLifecycleOwner) { note ->
            with(binding) {
                name.setText(note.title)
                date.text = note.createdAt.toFormatString()
                description.setText(note.description)
            }
        }
    }

    override fun onStop() {
        super.onStop()

        with(binding) {
            editNotesViewModel.updateNote(name.text.toString(), description.text.toString())
        }
    }

}