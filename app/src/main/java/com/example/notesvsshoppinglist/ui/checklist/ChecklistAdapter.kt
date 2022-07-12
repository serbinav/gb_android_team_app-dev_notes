package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.databinding.ItemChecklistBinding

class ChecklistAdapter : RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>() {

    private var data: List<ChecklistData> = arrayListOf()

    fun setData(data: List<ChecklistData>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChecklistAdapter.ChecklistViewHolder {
        val binding =
            ItemChecklistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ChecklistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChecklistAdapter.ChecklistViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ChecklistViewHolder(private val binding: ItemChecklistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ChecklistData) {
            with(binding) {
                name.text = data.notes.name
                date.text = data.notes.date
                progressBar.progress =
                    if (data.countDoneTask() != 0 && data.listTasks.isNotEmpty()) {
                        (100 * data.countDoneTask()) / data.listTasks.size
                    } else {
                        0
                    }
                progressBarCompletedTasks.text =
                    date.context.getString(
                        R.string.completed_tasks_format,
                        data.countDoneTask().toString(),
                        data.listTasks.size.toString()
                    )
            }
        }
    }
}