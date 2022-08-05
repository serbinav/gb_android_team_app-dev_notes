package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.FragmentNotesBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : BaseFragment<FragmentNotesBinding>(FragmentNotesBinding::inflate) {

    private val notesViewModel: NotesViewModel by viewModel()

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter { data ->
            val bundle = bundleOf(EditNotesFragment.NOTE_ID to data.id)
            findNavController()
                .navigate(R.id.action_navigation_notes_to_navigation_add_notes, bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerNotes.apply { this.adapter = notesAdapter }

        notesViewModel.notes.observe(viewLifecycleOwner) { notes ->
            notesAdapter.submitList(notes)
        }

        binding.fabNotes.setOnClickListener {
            findNavController()
                .navigate(R.id.action_navigation_notes_to_navigation_add_notes, null)
        }
    }
}