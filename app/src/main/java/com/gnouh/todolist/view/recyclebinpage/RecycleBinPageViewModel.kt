package com.gnouh.todolist.view.recyclebinpage

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


class RecycleBinPageViewModel(private val context: Application) : AndroidViewModel(context) {

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

    fun getTaskDel(): LiveData<List<Task>> = TaskRepository.getInstance(context).getTaskDel()

    fun createDate(hourOfDay: Int, minute: Int, dayOfMonth: Int, month: Int, year: Int): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
        cal.set(Calendar.MINUTE, minute)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        cal.set(Calendar.MONTH, month)
        cal.set(Calendar.YEAR, year)
        return cal.time
    }

    fun scheduleNotification(task: Task) {
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
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, task.deadline - MILLIS_IN_A_HOUR, pendingIntent)
    }
}