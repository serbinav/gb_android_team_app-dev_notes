package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.FragmentNotesBinding

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NotesViewModel by lazy {
        ViewModelProvider(this).get(NotesViewModel::class.java)
    }
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
        viewModel.text.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        adapter.onItemClick = { data ->
            val bundle = bundleOf(
                "name" to data.name,
                "date" to data.date,
                "description" to data.description
            )
            view.findNavController()
                .navigate(R.id.action_navigation_notes_to_navigation_add_notes, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}