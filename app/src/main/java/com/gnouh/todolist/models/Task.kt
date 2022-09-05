package com.gnouh.todolist.models

import android.annotation.SuppressLint
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class Task(var title: String, var description: String = "", var deadline: String) : Serializable {
    var timeCreate: String
    companion object {
        private const val pattern = "dd/MM/yyyy HH:mm"
        @SuppressLint("SimpleDateFormat")
        val simpleDateFormat = SimpleDateFormat(pattern)
    }
    init {
        timeCreate = simpleDateFormat.format(Calendar.getInstance().time)
        val date = LocalDate.parse(timeCreate)
    }
}