//package com.gnouh.todolist.view.calendarpage
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import com.gnouh.todolist.database.TaskRepository
//import com.gnouh.todolist.models.Task
//
//class CalendarPageViewModel : ViewModel() {
//
//    var listTaskLiveData: LiveData<List<Task>>
//        get() {
//            TODO()
//        }
//        set(value) {
//            field = value.value.filter {task ->
//                task.deadline >= date.time && task.deadline <= nextDay.time
//            }
//        }
//}