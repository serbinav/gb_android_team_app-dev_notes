package com.example.notesvsshoppinglist.ui.checklist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.notesvsshoppinglist.databinding.FragmentEditChecklistBinding

class EditChecklistFragment : Fragment() {

    private var _binding: FragmentEditChecklistBinding? = null
    private val binding get() = _binding!!
    private val viewModel: EditChecklistViewModel by lazy {
        ViewModelProvider(this).get(EditChecklistViewModel::class.java)
    }
    private lateinit var adapter: TodoAdapter

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
        val checklist = arguments?.getParcelable<ChecklistData>(ChecklistFragment.CHECKLIST_BUNDLE)
        val recycler = binding.recyclerChecklist
        if (checklist != null){
            binding.name.setText(checklist.notes.name)
            binding.date.setText(checklist.notes.date)
            binding.description.setText(checklist.notes.description)

            adapter = TodoAdapter(checklist.listTasks)
            recycler.adapter = adapter
            adapter.onItemClick = { data ->
                adapter.deleteItem(data)
                adapter.addItem(data)
            }
            adapter.onItemLongClick = { data ->
                adapter.deleteItem(data)
                adapter.addFirstItem(data)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}