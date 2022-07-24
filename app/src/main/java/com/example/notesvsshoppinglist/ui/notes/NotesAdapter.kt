package com.example.notesvsshoppinglist.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemNotesBinding
import com.rino.database.entity.Note
import com.example.notesvsshoppinglist.core.utils.toFormatString

class NotesAdapter(
    private val onItemClick: (Note) -> Unit
) : ListAdapter<Note, NotesAdapter.NotesViewHolder>(NoteItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val binding = ItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class NotesViewHolder(
        private val binding: ItemNotesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Note) {
            with(binding) {
                date.text = data.createdAt.toFormatString()
                name.text = data.title
                description.text = data.description

                root.setOnClickListener { onItemClick.invoke(data) }
            }
        }

    }
}

class NoteItemCallback : DiffUtil.ItemCallback<Note>() {
    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}