package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.FragmentEditChecklistBinding

class EditChecklistFragment : Fragment() {

    private var _binding: FragmentEditChecklistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditChecklistViewModel by lazy {
        ViewModelProvider(this).get(EditChecklistViewModel::class.java)
    }
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditChecklistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checklist = arguments?.getParcelable<ChecklistWithTask>(ChecklistFragment.CHECKLIST_BUNDLE)
        val recycler = binding.recyclerChecklist
        if (checklist != null){
            binding.name.setText(checklist.title)
            binding.date.setText(checklist.createdAt.toFormatString())
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}