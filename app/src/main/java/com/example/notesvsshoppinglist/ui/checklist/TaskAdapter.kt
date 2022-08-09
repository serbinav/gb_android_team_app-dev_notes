package com.example.notesvsshoppinglist.ui.checklist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemTaskBinding
import com.rino.database.entity.ChecklistTask

class TaskAdapter(private val editChecklistViewModel: EditChecklistViewModel) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var onItemUnmarked: ((ChecklistTask) -> Unit)? = null
    var onItemMarked: ((ChecklistTask) -> Unit)? = null
    var onItemLongClick: ((ChecklistTask, Int) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TaskAdapter.TaskViewHolder {
        val binding =
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        editChecklistViewModel.currentChecklist.value?.let {
            holder.bind(it.tasks[position])
        }
    }

    override fun getItemCount(): Int {
        editChecklistViewModel.currentChecklist.value?.let {
            return it.tasks.size
        }
        return 0
    }

    fun deleteItem(position: Int, checklistTaskId: Long) {
        editChecklistViewModel.deleteChecklistTask(checklistTaskId)
        notifyItemRemoved(position)
    }

    fun addItem(task: ChecklistTask) {
        editChecklistViewModel.updateChecklistTask(task)
        notifyItemInserted(itemCount - 1)
    }

    fun editItem(index: Int, task: ChecklistTask) {
        editChecklistViewModel.updateChecklistTask(task)
        notifyItemChanged(index)
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChecklistTask) {
            with(binding) {
                name.text = data.title
                name.isChecked = data.isMarked
                if (data.isMarked) {
                    name.setBackgroundColor(Color.parseColor("#E0E0E0"))
                } else {
                    name.setBackgroundColor(Color.parseColor("#FFFFFFFF"))
                }
            }
            binding.name.setOnLongClickListener {
                onItemLongClick?.invoke(data, adapterPosition)
                true
            }
            binding.name.setOnClickListener {
                data.apply {
                    if (data.isMarked) {
                        onItemMarked?.invoke(
                            ChecklistTask(this.id, this.checklistId, this.title, false)
                        )
                    } else {
                        onItemUnmarked?.invoke(
                            ChecklistTask(this.id, this.checklistId, this.title, true)
                        )
                    }
                }
            }
        }
    }
}