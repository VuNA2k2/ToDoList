package com.gnouh.todolist.view.homepage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnouh.todolist.R
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.constants.TIME_FORMAT
import com.gnouh.todolist.databinding.TaskAllItemBinding
import com.gnouh.todolist.models.Task
import java.util.*

class TaskAllAdapter(val delete: (Task) -> Unit, val update: (Task) -> Unit) : RecyclerView.Adapter<TaskAllAdapter.ViewHolder>() {

    var data: List<Task> = listOf()
        get() {
            return field
        }
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var binding: TaskAllItemBinding = TaskAllItemBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_all_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = data[position].title
            tvDescription.text = data[position].description
            tvDay.text = DATE_FORMAT.format(Date(data[position].deadline))
            tvTime.text = TIME_FORMAT.format(Date(data[position].deadline))
            btnDelete.setOnClickListener {
                delete(data[position])
            }
        }
        holder.view.setOnClickListener {
            update(data[position])
        }
    }

    override fun getItemCount(): Int = data.size

}