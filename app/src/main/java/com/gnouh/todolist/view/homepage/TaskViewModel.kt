package com.gnouh.todolist.view.homepage

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.constants.MILLIS_IN_A_DAY
import com.gnouh.todolist.database.TaskRepository
import com.gnouh.todolist.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class TaskViewModel(private val context: Application) : AndroidViewModel(context) {

    fun getAllTask(): LiveData<List<Task>> = TaskRepository.getInstance(context).getAllTask()

    fun insert(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            TaskRepository.getInstance(context).insert(task)
        }
    }

    fun update(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            TaskRepository.getInstance(context).update(task)
        }
    }

    fun delete(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            TaskRepository.getInstance(context).delete(task)
        }
    }

    fun getTaskByDay(date: Date): LiveData<List<Task>> {
        val currentDay = DATE_FORMAT.parse(DATE_FORMAT.format(date))
        val nextDay = Date(currentDay!!.time + MILLIS_IN_A_DAY)
        return TaskRepository.getInstance(context).getTaskByDay(currentDay.time, nextDay.time)
    }

    fun getTaskDel(): LiveData<List<Task>> = TaskRepository.getInstance(context).getTaskDel()

}