package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.ItemChecklistBinding
import com.rino.database.entity.ChecklistWithTasks

class ChecklistAdapter(
    private val onItemClick: (ChecklistWithTasks) -> Unit
) : ListAdapter<ChecklistWithTasks, ChecklistAdapter.ChecklistViewHolder>(ChecklistItemCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChecklistAdapter.ChecklistViewHolder {
        val binding =
            ItemChecklistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChecklistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChecklistAdapter.ChecklistViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class ChecklistViewHolder(
        private val binding: ItemChecklistBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChecklistWithTasks) {
            with(binding) {
                name.text = data.checklist.title
                date.text = data.checklist.createdAt.toFormatString()
                progressBar.progress =
                    if (data.numberOfCompletedTasks != 0) {
                        (100 * data.numberOfCompletedTasks) / data.tasks.size
                    } else {
                        0
                    }
                progressBarCompletedTasks.text =
                    date.context.getString(
                        R.string.completed_tasks_format,
                        data.numberOfCompletedTasks.toString(),
                        data.tasks.size.toString()
                    )

                root.setOnClickListener { onItemClick.invoke(data) }
            }

        }
    }
}

class ChecklistItemCallback : DiffUtil.ItemCallback<ChecklistWithTasks>() {
    override fun areItemsTheSame(
        oldItem: ChecklistWithTasks,
        newItem: ChecklistWithTasks
    ): Boolean {
        return oldItem.checklist.id == newItem.checklist.id
    }

    override fun areContentsTheSame(
        oldItem: ChecklistWithTasks,
        newItem: ChecklistWithTasks
    ): Boolean {
        return oldItem == newItem
    }
}