package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.FragmentNotesBinding
import com.example.notesvsshoppinglist.core.utils.toFormatString
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private val notesViewModel: NotesViewModel by viewModel()

    private val adapter: NotesAdapter by lazy { NotesAdapter() }

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
        val recycler = binding.recyclerNotes
        recycler.adapter = adapter
        notesViewModel.notes.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        adapter.onItemClick = { data ->
            val bundle = bundleOf(NOTES_BUNDLE to data)
            view.findNavController()
                .navigate(R.id.action_navigation_notes_to_navigation_add_notes, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val NOTES_BUNDLE = "notes"
    }
}