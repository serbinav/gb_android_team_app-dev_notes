package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemTodoBinding

class TodoAdapter(private var data: ArrayList<ToDoData>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    var onItemClick: ((ToDoData) -> Unit)? = null
    var onItemLongClick: ((ToDoData) -> Unit)? = null

    fun updateReceipt(list: List<ToDoData>) {
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

    fun deleteItem(item: ToDoData) {
        data.remove(item)
        notifyDataSetChanged()
    }

    fun addItem(item: ToDoData) {
        data.add(item)
        notifyDataSetChanged()
    }

    fun addFirstItem(item: ToDoData) {
        data.add(0, item)
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ToDoData) {
            with(binding) {
                name.text = data.name
                name.isChecked = data.isDone
                name.isEnabled = !data.isDone
            }
            binding.name.setOnClickListener {
                val dataChanged = data
                dataChanged.isDone = !data.isDone
                onItemClick?.invoke(dataChanged)
            }

            itemView.setOnLongClickListener {
                val dataChanged = data
                dataChanged.isDone = !data.isDone
                onItemLongClick?.invoke(dataChanged)
                true
            }
        }
    }
}