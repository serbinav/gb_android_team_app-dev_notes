package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.model.ChecklistWithCounters
import com.example.notesvsshoppinglist.databinding.ItemChecklistBinding

class ChecklistAdapter : RecyclerView.Adapter<ChecklistAdapter.NotesViewHolder>() {

    private var data: List<ChecklistWithCounters> = arrayListOf()

    fun setData(data: List<ChecklistWithCounters>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChecklistAdapter.NotesViewHolder {
        val binding =
            ItemChecklistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return NotesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChecklistAdapter.NotesViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class NotesViewHolder(private val binding: ItemChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChecklistWithCounters) {
            with(binding) {
                date.text = data.date
                name.text = data.name
                progressBar.progress =
                    (100 * data.currentNumberCompletedTasks) / data.totalTasksCount
                progressBarCompletedTasks.text =
                    date.context.getString(
                        R.string.completed_tasks_format,
                        data.currentNumberCompletedTasks.toString(),
                        data.totalTasksCount.toString()
                    )
            }
        }
    }
}