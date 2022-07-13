package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.FragmentChecklistBinding

class ChecklistFragment : Fragment() {

    private var _binding: FragmentChecklistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ChecklistViewModel by lazy {
        ViewModelProvider(this).get(ChecklistViewModel::class.java)
    }
    private val adapter: ChecklistAdapter by lazy { ChecklistAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChecklistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.recyclerChecklist
        recycler.adapter = adapter
        viewModel.text.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        adapter.onItemClick = { data ->
            val bundle = bundleOf(CHECKLIST_BUNDLE to data)
            view.findNavController()
                .navigate(R.id.action_navigation_checklist_to_navigation_add_checklist, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val CHECKLIST_BUNDLE = "checklist"
    }
}