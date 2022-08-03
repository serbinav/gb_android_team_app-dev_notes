package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.FragmentEditChecklistBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import com.rino.database.entity.ChecklistTask
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditChecklistFragment :
    BaseFragment<FragmentEditChecklistBinding>(FragmentEditChecklistBinding::inflate) {

    private val editChecklistViewModel: EditChecklistViewModel by viewModel()
    private val viewModel: EditChecklistViewModel by lazy {
        ViewModelProvider(this).get(EditChecklistViewModel::class.java)
    }
    private lateinit var adapter: TaskAdapter
    private lateinit var checklistTask: ChecklistTask
    private var position: Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checklistWithTask =
            arguments?.getParcelable<ChecklistWithTask>(ChecklistFragment.CHECKLIST_BUNDLE)
        val recycler = binding.recyclerChecklist
        registerForContextMenu(recycler)

        if (checklistWithTask != null) {
            binding.name.setText(checklistWithTask.checklist.title)
            binding.date.text = checklistWithTask.checklist.createdAt.toFormatString()
            binding.description.setText(checklistWithTask.checklist.description)

            adapter = TaskAdapter(checklistWithTask.listTask.toCollection(arrayListOf()))
            recycler.adapter = adapter
            adapter.onItemUnmarked = { data ->
                adapter.addItem(data)
            }
            adapter.onItemMarked = { data ->
                adapter.addFirstItem(data)
            }
            adapter.onItemLongClick = { data, pos ->
                checklistTask = data
                position = pos
                recycler.showContextMenu()
            }
        }
        binding.buttonAddTask.setOnClickListener {
            showAddOrEditDialog(
                taskAdapter = adapter,
                lambda = { _, task: ChecklistTask -> adapter.addFirstItem(task) })
        }
    }

    private fun showAddOrEditDialog(
        taskName: String = "",
        taskPosition: Int = 0,
        taskMark: Boolean = false,
        taskAdapter: TaskAdapter,
        @DrawableRes
        dialogIcon: Int = R.drawable.ic_baseline_playlist_add_24,
        lambda: (Int, ChecklistTask) -> Unit
    ) {
        val view: View =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_edit, null, false)
        val editText = view.findViewById<AppCompatEditText>(R.id.edit_elem)
        editText.setText(taskName)
        val imageView = view.findViewById<AppCompatImageView>(R.id.img_header)
        imageView.setImageDrawable(AppCompatResources.getDrawable(requireContext(), dialogIcon))
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton(R.string.alert_positive_btn) { _, _ ->
                if (editText.text != null && editText.text.toString().isNotEmpty()) {
                    val task = ChecklistTask(
                        id = taskAdapter.itemCount.toLong(),
                        checklistId = taskAdapter.getChecklistId(),
                        title = editText.text.toString(),
                        isMarked = taskMark
                    )
                    lambda(taskPosition, task)
                }
            }
            .setNegativeButton(R.string.alert_negative_btn) { _, _ -> }
        builder.show()
        editText.requestFocus()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.act_edit -> showAddOrEditDialog(
                taskName = checklistTask.title,
                taskPosition = position,
                taskMark = checklistTask.isMarked,
                taskAdapter = adapter,
                dialogIcon = R.drawable.ic_baseline_edit_note_24,
                lambda = { index, task -> adapter.editItem(index, task) })
            R.id.act_delete -> adapter.deleteItem(position)
            //добавить запрос подтверждение удаления
        }
        return true
    }
}