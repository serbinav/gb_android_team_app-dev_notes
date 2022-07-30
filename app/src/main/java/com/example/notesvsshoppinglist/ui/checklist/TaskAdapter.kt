package com.example.notesvsshoppinglist.ui.checklist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemTaskBinding
import com.rino.database.entity.ChecklistTask

class TaskAdapter(private var data: ArrayList<ChecklistTask>) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    var onItemUnmarked: ((ChecklistTask) -> Unit)? = null
    var onItemMarked: ((ChecklistTask) -> Unit)? = null

    fun updateReceipt(list: List<ChecklistTask>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

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
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deleteItem(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addItem(item: ChecklistTask) {
        data.add(item)
        notifyItemInserted(data.size - 1)
    }

    fun addFirstItem(item: ChecklistTask) {
        data.add(0, item)
        notifyItemInserted(0)
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
            val dataChanged = data
            binding.name.setOnClickListener {
                dataChanged.apply {
                    deleteItem(adapterPosition)
                    if (dataChanged.isMarked) {
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