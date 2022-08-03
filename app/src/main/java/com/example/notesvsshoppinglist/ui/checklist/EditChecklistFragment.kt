package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.ViewModelProvider
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.DialogEditBinding
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
        val dialogBinding = DialogEditBinding.inflate(layoutInflater, null, false)
        with(dialogBinding) {
            editElem.setText(taskName)
            imgHeader.setImageDrawable(
                AppCompatResources.getDrawable(
                    requireContext(),
                    dialogIcon
                )
            )
            val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                .setView(root)
                .setPositiveButton(R.string.alert_positive_btn) { _, _ ->
                    if (editElem.text.isNullOrEmpty().not()) {
                        val task = ChecklistTask(
                            id = taskAdapter.itemCount.toLong(),
                            checklistId = taskAdapter.getChecklistId(),
                            title = editElem.text.toString(),
                            isMarked = taskMark
                        )
                        lambda(taskPosition, task)
                    }
                }
                .setNegativeButton(R.string.alert_negative_btn) { _, _ -> }
            builder.show()
            editElem.requestFocus()
        }
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