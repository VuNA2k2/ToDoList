package com.gnouh.todolist.view.recyclebinpage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnouh.todolist.R
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.constants.TIME_FORMAT
import com.gnouh.todolist.databinding.TaskAllItemBinding
import com.gnouh.todolist.databinding.TaskDeletedItemBinding
import com.gnouh.todolist.models.Task
import com.gnouh.todolist.view.homepage.TaskAllAdapter
import java.util.*

class TaskDeletedAdapter(val restore: (Task) -> Unit, val delete: (Task) -> Unit) : RecyclerView.Adapter<TaskDeletedAdapter.ViewHolder>() {

    var data: List<Task> = listOf()
        get() {
            return field
        }
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: TaskDeletedItemBinding
        init {
            binding = TaskDeletedItemBinding.bind(itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_deleted_item, parent, false)
        return TaskDeletedAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = data[position].title
            tvDescription.text = data[position].description
            tvDay.text = DATE_FORMAT.format(Date(data[position].deadline))
            tvTime.text = TIME_FORMAT.format(Date(data[position].deadline))
            btnRestore.setOnClickListener {
                restore(data[position])
            }
            btnDelete.setOnClickListener {
                delete(data[position])
            }
        }
    }

    override fun getItemCount(): Int = data.size
}