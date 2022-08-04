package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.ItemChecklistBinding

class ChecklistAdapter(
    private val onItemClick: (ChecklistWithTask) -> Unit
) : ListAdapter<ChecklistWithTask, ChecklistAdapter.ChecklistViewHolder>(
    ChecklistItemCallback()
) {

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

    inner class ChecklistViewHolder(private val binding: ItemChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChecklistWithTask) {
            with(binding) {
                name.text = data.checklist.title
                date.text = data.checklist.createdAt.toFormatString()
                progressBar.progress =
                    if (data.countDoneTask() != 0 && data.listTask.isNotEmpty()) {
                        (100 * data.countDoneTask()) / data.listTask.size
                    } else {
                        0
                    }
                progressBarCompletedTasks.text =
                    date.context.getString(
                        R.string.completed_tasks_format,
                        data.countDoneTask().toString(),
                        data.listTask.size.toString()
                    )

                root.setOnClickListener { onItemClick.invoke(data) }
            }

        }
    }
}

class ChecklistItemCallback : DiffUtil.ItemCallback<ChecklistWithTask>() {
    override fun areItemsTheSame(oldItem: ChecklistWithTask, newItem: ChecklistWithTask): Boolean {
        return oldItem.checklist.id == newItem.checklist.id
    }

    override fun areContentsTheSame(
        oldItem: ChecklistWithTask,
        newItem: ChecklistWithTask
    ): Boolean {
        return oldItem == newItem
    }
}