package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.FragmentNotesBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by viewModel()

    private val notesAdapter: NotesAdapter by lazy {
        NotesAdapter { data ->
            val bundle = bundleOf(
                NAME_BUNDLE to data.title,
                DATE_BUNDLE to data.createdAt.toFormatString(),
                DESCRIPTION_BUNDLE to data.description
            )

            findNavController()
                .navigate(R.id.action_navigation_notes_to_navigation_add_notes, bundle)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerNotes.apply { this.adapter = notesAdapter }

        notesViewModel.notes.observe(viewLifecycleOwner) { notes ->
            notesAdapter.submitList(notes)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NAME_BUNDLE = "name_bundle"
        const val DATE_BUNDLE = "date_bundle"
        const val DESCRIPTION_BUNDLE = "description_bundle"
    }
}