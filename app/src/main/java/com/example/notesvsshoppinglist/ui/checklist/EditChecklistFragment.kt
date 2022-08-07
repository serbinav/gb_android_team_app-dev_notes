package com.example.notesvsshoppinglist.ui.checklist

import android.os.Bundle
import android.view.*
import android.view.ContextMenu.ContextMenuInfo
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.DialogEditBinding
import com.example.notesvsshoppinglist.databinding.FragmentEditChecklistBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import com.rino.database.entity.ChecklistTask
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditChecklistFragment :
    BaseFragment<FragmentEditChecklistBinding>(FragmentEditChecklistBinding::inflate) {

    private val editChecklistViewModel: EditChecklistViewModel by viewModel {
        parametersOf(arguments?.getLong(CHECKLIST_ID) ?: -1L)
    }

    private lateinit var adapter: TaskAdapter
    private var checklistId: Long = 0L
    private lateinit var checklistTask: ChecklistTask
    private var positionTask: Int = 0

    private val menuProvider by lazy {
        object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_delete -> {
                        editChecklistViewModel.deleteChecklist()
                        findNavController().popBackStack()
                        true
                    }

                    else -> false
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MenuHost)
            .addMenuProvider(menuProvider, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.recyclerChecklist
        registerForContextMenu(recycler)

        editChecklistViewModel.currentChecklist.observe(viewLifecycleOwner) { checklistWithTask ->
            checklistWithTask?.let {
                with(binding) {
                    name.setText(checklistWithTask.checklist.title)
                    date.text = checklistWithTask.checklist.createdAt.toFormatString()
                    description.setText(checklistWithTask.checklist.description)

                    adapter = TaskAdapter(checklistWithTask.tasks.toCollection(arrayListOf()))
                    recycler.adapter = adapter
                    adapter.onItemUnmarked = { data ->
                        adapter.addItem(data)
                    }
                    adapter.onItemMarked = { data ->
                        adapter.addFirstItem(data)
                    }
                    adapter.onItemLongClick = { data, pos ->
                        checklistTask = data
                        positionTask = pos
                        recycler.showContextMenu()
                    }
                    checklistId = checklistWithTask.checklist.id
                    buttonAddTask.setOnClickListener {
                        showAddOrEditDialog(
                            lambda = { _, title ->
                                val task = ChecklistTask(
                                    id = adapter.itemCount.toLong(),
                                    checklistId = checklistId,
                                    title = title,
                                    isMarked = false
                                )
                                adapter.addFirstItem(task)
                                editChecklistViewModel.updateChecklistTask(task)
                            }
                        )
                    }
                }
            }
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
                taskPosition = positionTask,
                dialogIcon = R.drawable.ic_baseline_edit_note_24,
                lambda = { index, title ->
                    val task = ChecklistTask(
                        id = checklistTask.id,
                        checklistId = checklistId,
                        title = title,
                        isMarked = checklistTask.isMarked
                    )
                    adapter.editItem(index, task)
                    editChecklistViewModel.updateChecklistTask(task)
                }
            )
            R.id.act_delete -> {
                adapter.deleteItem(positionTask)
                editChecklistViewModel.deleteChecklistTask(checklistTask.id)
            }
            //добавить запрос подтверждение удаления
        }
        return true
    }

    private fun showAddOrEditDialog(
        taskName: String = "",
        taskPosition: Int = 0,
        @DrawableRes
        dialogIcon: Int = R.drawable.ic_baseline_playlist_add_24,
        lambda: (Int, String) -> Unit
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
                        lambda(taskPosition, editElem.text.toString())
                    }
                }
                .setNegativeButton(R.string.alert_negative_btn) { _, _ -> }
            builder.show()
            editElem.requestFocus()
        }
    }

    override fun onStop() {
        super.onStop()

        with(binding) {
            editChecklistViewModel.updateChecklist(
                name.text.toString(),
                description.text.toString(),
                adapter.getItems()
            )
        }
    }

    companion object {
        const val CHECKLIST_ID = "checklist_id"
    }

}