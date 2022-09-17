package com.gnouh.todolist.database

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.gnouh.todolist.models.Task
import java.util.*

class TaskRepository private constructor(context: Application){
    private val taskDatabase = TaskDatabase.getInstance(context)
    companion object {
        private var INSTANCE: TaskRepository? = null
        fun getInstance(context: Application): TaskRepository {
            if (INSTANCE != null) {
                return INSTANCE!!
            }
            INSTANCE = TaskRepository(context)
            return INSTANCE!!
        }
    }

    fun getAllTask(): LiveData<List<Task>> = taskDatabase.taskDao().getAllTask()

    fun insert(task: Task) {
        taskDatabase.taskDao().insert(task)
    }

    fun update(task: Task) {
        taskDatabase.taskDao().update(task)
    }

    fun delete(task: Task) {
        taskDatabase.taskDao().delete(task)
    }

    fun getTaskByDay(currentDay: Long, nextDay: Long): LiveData<List<Task>> = taskDatabase.taskDao().getTaskByDay(currentDay, nextDay)

    fun getTaskDel(): LiveData<List<Task>> = taskDatabase.taskDao().getTaskDel()
}