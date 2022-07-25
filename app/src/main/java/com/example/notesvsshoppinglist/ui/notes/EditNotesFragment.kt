package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.View
import com.example.notesvsshoppinglist.databinding.FragmentEditNotesBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditNotesFragment :
    BaseFragment<FragmentEditNotesBinding>(FragmentEditNotesBinding::inflate) {

    private val editNotesViewModel: EditNotesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.setText(arguments?.getString(NotesFragment.NAME_BUNDLE))
        binding.date.text = arguments?.getString(NotesFragment.DATE_BUNDLE)
        binding.description.setText(arguments?.getString(NotesFragment.DESCRIPTION_BUNDLE))
    }

}