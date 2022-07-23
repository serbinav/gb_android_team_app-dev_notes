package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesvsshoppinglist.databinding.FragmentEditNotesBinding

class EditNotesFragment : Fragment() {

    private var _binding: FragmentEditNotesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditNotesViewModel by lazy {
        ViewModelProvider(this).get(EditNotesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.setText(arguments?.getString(NotesFragment.NAME_BUNDLE))
        binding.date.text = arguments?.getString(NotesFragment.DATE_BUNDLE)
        binding.description.setText(arguments?.getString(NotesFragment.DESCRIPTION_BUNDLE))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}