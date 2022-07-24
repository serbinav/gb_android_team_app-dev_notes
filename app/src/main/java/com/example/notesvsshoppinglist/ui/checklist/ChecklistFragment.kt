package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.FragmentChecklistBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChecklistFragment :
    BaseFragment<FragmentChecklistBinding>(FragmentChecklistBinding::inflate) {

    private val checklistViewModel: ChecklistViewModel by viewModel()

    private val adapter: ChecklistAdapter by lazy { ChecklistAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.recyclerChecklist
        recycler.adapter = adapter
        checklistViewModel.checklists.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

        adapter.onItemClick = { data ->
            val bundle = bundleOf(CHECKLIST_BUNDLE to data)
            view.findNavController()
                .navigate(R.id.action_navigation_checklist_to_navigation_add_checklist, bundle)
        }
    }

    companion object {
        const val CHECKLIST_BUNDLE = "checklist"
    }
}