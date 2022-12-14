package com.gnouh.todolist.models

import android.annotation.SuppressLint
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gnouh.todolist.constants.*
import java.io.Serializable
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

@Entity(tableName = TASK_TABLE_NAME, )
class Task(
    @ColumnInfo(name = TASK_NAME_COL) var title: String,
    @ColumnInfo(name = TASK_DESCRIPTION_COL) var description: String = "",
    @ColumnInfo(name = TASK_DEADLINE_COL) var deadline: Long,
    @ColumnInfo(name = TASK_COMPLETE_COL) var isComplete: Boolean = false,
    @ColumnInfo(name = TASK_DELETE_COL) var isDelete: Boolean = false
) : Serializable {
    @ColumnInfo(name = TASK_TIME_CREATE_COL) var timeCreate: Long = Calendar.getInstance().time.time
    @PrimaryKey(autoGenerate = true)
    var id = 0
}