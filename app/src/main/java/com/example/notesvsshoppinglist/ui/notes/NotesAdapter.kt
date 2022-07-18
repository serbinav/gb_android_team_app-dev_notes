package com.example.notesvsshoppinglist.ui.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemNotesBinding
import com.rino.database.entity.Note
import com.example.notesvsshoppinglist.core.utils.toFormatString

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var data: List<Note> = arrayListOf()
    var onItemClick: ((Note) -> Unit)? = null

    fun setData(data: List<Note>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        val binding =
            ItemNotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class NotesViewHolder(private val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Note) {
            with(binding) {
                date.text = data.createdAt.toFormatString()
                name.text = data.title
                description.text = data.description
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }
}