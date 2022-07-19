package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemTodoBinding
import com.rino.database.entity.ChecklistTask

class TodoAdapter(private var data: ArrayList<ChecklistTask>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var onItemClick: ((ChecklistTask) -> Unit)? = null
    var onItemLongClick: ((ChecklistTask) -> Unit)? = null

    fun updateReceipt(list: List<ChecklistTask>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodoAdapter.TodoViewHolder {
        val binding =
            ItemTodoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun deleteItem(item: ChecklistTask) {
        data.remove(item)
        notifyDataSetChanged()
    }

    fun addItem(item: ChecklistTask) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun addFirstItem(item: ChecklistTask) {
        data.add(0, item)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChecklistTask) {
            with(binding) {
                name.text = data.title
                name.isChecked = data.isMarked
                name.isEnabled = !data.isMarked
            }
            val dataChanged = data
            itemView.setOnClickListener {
                if (!dataChanged.isMarked) {
                    dataChanged.apply {
                        deleteItem(this)
                        onItemClick?.invoke(
                            ChecklistTask(this.id, this.checklistId, this.title, true)
                        )
                    }
                }
            }
            itemView.setOnLongClickListener {
                if (dataChanged.isMarked) {
                    dataChanged.apply {
                        deleteItem(this)
                        onItemLongClick?.invoke(
                            ChecklistTask(this.id, this.checklistId, this.title, false)
                        )
                    }
                }
                true
            }
        }
    }
}