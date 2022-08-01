package com.example.notesvsshoppinglist.ui.notes

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.FragmentEditNotesBinding
import com.example.notesvsshoppinglist.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class EditNotesFragment :
    BaseFragment<FragmentEditNotesBinding>(FragmentEditNotesBinding::inflate) {

    companion object {
        const val NOTE_ID = "NOTE_ID"
    }

    private val editNotesViewModel: EditNotesViewModel by viewModel {
        parametersOf(arguments?.getLong(NOTE_ID) ?: -1L)
    }

    private val menuProvider by lazy {
        object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_note_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_delete -> {
                        editNotesViewModel.deleteNote()
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

        editNotesViewModel.currentNote.observe(viewLifecycleOwner) { note ->
            note?.let {
                with(binding) {
                    name.setText(it.title)
                    date.text = it.createdAt.toFormatString()
                    description.setText(it.description)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()

        with(binding) {
            editNotesViewModel.updateNote(name.text.toString(), description.text.toString())
        }
    }

}