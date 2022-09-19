package com.gnouh.todolist.view.calendarpage

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gnouh.todolist.R
import com.gnouh.todolist.constants.TIME_FORMAT
import com.gnouh.todolist.databinding.TaskByDayItemBinding
import com.gnouh.todolist.models.Task
import java.util.*

class CalendarTaskAdapter(val change: (Task, Boolean) -> Unit) : RecyclerView.Adapter<CalendarTaskAdapter.ViewHolder>() {

    var data: List<Task> = listOf()
        get() {
            return field
        }
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding: TaskByDayItemBinding
        init {
            binding = TaskByDayItemBinding.bind(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_by_day_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            tvTitle.text = data[position].title
            tvDescription.text = data[position].description
            tvTime.text = TIME_FORMAT.format(Date(data[position].deadline))
            swOnOff.isChecked = !data[position].isComplete
            swOnOff.setOnCheckedChangeListener { compoundButton, check ->
                change(data[position], check)
            }
        }
    }

    override fun getItemCount(): Int = data.size
}