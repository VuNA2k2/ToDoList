package com.gnouh.todolist.view.homepage

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gnouh.todolist.broadcast.AlarmBroadcast
import com.gnouh.todolist.constants.*
import com.gnouh.todolist.database.TaskRepository
import com.gnouh.todolist.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


class TaskViewModel(private val context: Application) : AndroidViewModel(context) {

    fun getAllTask(): LiveData<List<Task>> = TaskRepository.getInstance(context).getAllTask()

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

    fun getTaskBySearch(search: String): LiveData<List<Task>> = TaskRepository.getInstance(context).getTaskBySearch(search)

    fun cancelNotification(task: Task) {
        val intent = Intent(context, AlarmBroadcast::class.java)
        intent.putExtra(TITLE_EXTRA, task.title)
        intent.putExtra(NOTIFY_ID, task.id)
        intent.putExtra(DESCRIPTION_EXTRA, task.description)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            task.id,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)
    }
}