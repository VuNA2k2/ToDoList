package com.gnouh.todolist.database
import com.gnouh.todolist.constants.TASK_DEADLINE_COL
import androidx.lifecycle.LiveData
import androidx.room.*
import com.gnouh.todolist.models.Task
import java.util.*

@Dao
interface TaskDAO {
    @Query("SELECT * FROM TASK_TABLE WHERE DEL = 0")
    fun getAllTask(): LiveData<List<Task>>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM TASK_TABLE WHERE DEADLINE > :currentDay AND DEADLINE < :nextDay AND DEL = 0")
    fun getTaskByDay(currentDay: Long, nextDay: Long): LiveData<List<Task>>

    @Query("SELECT * FROM TASK_TABLE WHERE DEL = 1")
    fun getTaskDel(): LiveData<List<Task>>

    @Query("SELECT * FROM TASK_TABLE WHERE DEL = 0 AND (NAME LIKE :search OR DESCRIPTION LIKE :search)")
    fun getTaskBySearch(search: String): LiveData<List<Task>>
}