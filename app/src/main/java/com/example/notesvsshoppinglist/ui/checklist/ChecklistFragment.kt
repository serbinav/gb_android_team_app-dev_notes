package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.FragmentChecklistBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChecklistFragment :
    BaseFragment<FragmentChecklistBinding>(FragmentChecklistBinding::inflate) {

    private val checklistViewModel: ChecklistViewModel by viewModel()

    private val checklistAdapter: ChecklistAdapter by lazy {
        ChecklistAdapter { data ->
            val bundle = bundleOf(EditChecklistFragment.CHECKLIST_ID to data.checklist.id)
            findNavController()
                .navigate(R.id.action_navigation_checklist_to_navigation_add_checklist, bundle)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerChecklist.apply {
            this.adapter = checklistAdapter
        }

        checklistViewModel.checklists.observe(viewLifecycleOwner) {
            checklistAdapter.submitList(it)
        }

        binding.fabChecklist.setOnClickListener {
            findNavController()
                .navigate(R.id.action_navigation_checklist_to_navigation_add_checklist, null)
        }
    }
}