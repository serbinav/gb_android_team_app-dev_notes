package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.FragmentEditChecklistBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditChecklistFragment :
    BaseFragment<FragmentEditChecklistBinding>(FragmentEditChecklistBinding::inflate) {

    private val editChecklistViewModel: EditChecklistViewModel by viewModel()
    private val viewModel: EditChecklistViewModel by lazy {
        ViewModelProvider(this).get(EditChecklistViewModel::class.java)
    }
    private lateinit var adapter: TaskAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checklist =
            arguments?.getParcelable<ChecklistWithTask>(ChecklistFragment.CHECKLIST_BUNDLE)
        val recycler = binding.recyclerChecklist
        if (checklist != null) {
            binding.name.setText(checklist.title)
            binding.date.text = checklist.createdAt.toFormatString()
            binding.description.setText(checklist.description)

            adapter = TaskAdapter(checklist.listTask.toCollection(arrayListOf()))
            recycler.adapter = adapter
            adapter.onItemUnmarked = { data ->
                adapter.addItem(data)
            }
            adapter.onItemMarked = { data ->
                adapter.addFirstItem(data)
            }
        }
    }

}