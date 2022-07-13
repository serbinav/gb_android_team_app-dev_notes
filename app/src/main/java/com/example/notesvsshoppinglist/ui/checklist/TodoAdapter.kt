package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.databinding.ItemTodoBinding

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var data: List<ToDoData> = arrayListOf()

    fun setData(data: List<ToDoData>) {
        this.data = data
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

    inner class TodoViewHolder(private val binding: ItemTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ToDoData) {
            with(binding) {
                name.text = data.name
            }
        }
    }
}