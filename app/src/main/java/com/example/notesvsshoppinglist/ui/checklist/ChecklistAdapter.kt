package com.example.notesvsshoppinglist.ui.checklist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesvsshoppinglist.R
import com.example.notesvsshoppinglist.core.model.ChecklistWithTask
import com.example.notesvsshoppinglist.core.utils.Utils
import com.example.notesvsshoppinglist.core.utils.toFormatString
import com.example.notesvsshoppinglist.databinding.ItemChecklistBinding

class ChecklistAdapter : RecyclerView.Adapter<ChecklistAdapter.ChecklistViewHolder>() {

    private var data: List<ChecklistWithTask> = arrayListOf()
    var onItemClick: ((ChecklistWithTask) -> Unit)? = null

    fun setData(data: List<ChecklistWithTask>) {
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

        fun bind(data: ChecklistWithTask) {
            with(binding) {
                name.text = data.title
                date.text = data.createdAt.toFormatString()
                progressBar.progress =
                    if (Utils.countDoneTask(data) != 0 && data.listTask.isNotEmpty()) {
                        (100 * Utils.countDoneTask(data)) / data.listTask.size
                    } else {
                        0
                    }
                progressBarCompletedTasks.text =
                    date.context.getString(
                        R.string.completed_tasks_format,
                        Utils.countDoneTask(data).toString(),
                        data.listTask.size.toString()
                    )
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }
}