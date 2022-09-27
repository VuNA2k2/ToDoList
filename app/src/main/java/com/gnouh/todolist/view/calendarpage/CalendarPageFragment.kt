package com.gnouh.todolist.view.calendarpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.databinding.FragmentCalendarPageBinding
import com.gnouh.todolist.models.Task
import java.text.SimpleDateFormat
import java.util.*


class CalendarPageFragment : Fragment() {
    private lateinit var calendarPageBinding: FragmentCalendarPageBinding
    @SuppressLint("SimpleDateFormat")
    private val monthFormat = SimpleDateFormat("MMMM")
    private lateinit var taskByDayViewModel: CalendarPageViewModel
    private lateinit var taskByDayAdapter: CalendarTaskAdapter
    private var currentDay: MutableLiveData<Date> = MutableLiveData(DATE_FORMAT.parse(DATE_FORMAT.format(Calendar.getInstance().time)))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskByDayViewModel = ViewModelProvider(this)[CalendarPageViewModel::class.java]

        taskByDayAdapter = CalendarTaskAdapter() { it, _ ->
            val task: Task = it
            task.isComplete = !task.isComplete
            taskByDayViewModel.update(task)
            if (task.isComplete) {
                taskByDayViewModel.cancelNotification(it)
            } else {
                taskByDayViewModel.scheduleNotification(it)
            }
        }


        calendarPageBinding = FragmentCalendarPageBinding.inflate(inflater, container, false)
        calendarPageBinding.apply {
            listTaskByDay.layoutManager = LinearLayoutManager(context)
            listTaskByDay.setHasFixedSize(true)
            listTaskByDay.adapter = taskByDayAdapter

            tvDay.text = DATE_FORMAT.format(currentDay.value!!)

//            taskViewModel.getTaskByDay(currentDay.value!!).observe(viewLifecycleOwner) { listTasks ->
//                taskAllAdapter.data = listTasks
//                if (taskAllAdapter.data.isNotEmpty()) {
//                    calendarPageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
//                } else {
//                    calendarPageBinding.imgEmptyAllTask.visibility = View.VISIBLE
//                }
//            }

//            fabAdd.setOnClickListener {
//                taskViewModel.insert(Task(title = "Task from calendar", description = "Description", deadline = currentDay.value!!.time + MILLIS_IN_A_DAY / 2))
//                Log.e("CLICK", "onCreateView: fab on clicked ${currentDay.value!!.time}", )
//            }

            calendarView.setListener(object : CompactCalendarViewListener {
                override fun onDayClick(dateClicked: Date?) {
                    Log.e("CALENDAR", "onDayClick: ${dateClicked?.time ?: 0 }", )
                    tvDay.text = dateClicked?.let {
                        currentDay.value = it
//                        taskViewModel.getTaskByDay(it).observe(viewLifecycleOwner) { listTasks ->
//                            taskAllAdapter.data = listTasks
//                            if (taskAllAdapter.data.isNotEmpty()) {
//                                calendarPageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
//                            } else {
//                                calendarPageBinding.imgEmptyAllTask.visibility = View.VISIBLE
//                            }
//                        }
                        DATE_FORMAT.format(it)
                    }
                }

                override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                    tvDay.text = firstDayOfNewMonth?.let {
                        currentDay.value = it
//                        taskViewModel.getTaskByDay(it).observe(viewLifecycleOwner) { listTasks ->
//                            taskAllAdapter.data = listTasks
//                            if (taskAllAdapter.data.isNotEmpty()) {
//                                calendarPageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
//                            } else {
//                                calendarPageBinding.imgEmptyAllTask.visibility = View.VISIBLE
//                            }
//                        }
                        DATE_FORMAT.format(it)
                    }
                    tvSelectMonth.text = firstDayOfNewMonth?.let { monthFormat.format(it) }
                }
            })

        }
        currentDay.observe(viewLifecycleOwner) {
            Log.e("SELECTDAY", "currentDay observe: ${it.time}", )
            taskByDayViewModel.getTaskByDay(it).observe(viewLifecycleOwner) { listTasks ->
                taskByDayAdapter.data = listTasks
                if (taskByDayAdapter.data.isNotEmpty()) {
                    calendarPageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
                } else {
                    calendarPageBinding.imgEmptyAllTask.visibility = View.VISIBLE
                }
            }
        }

        return calendarPageBinding.root
    }

//    fun getTaskByDay(date: Date?, listTasks: List<Task>): List<Task> {
//        val currentDay = DATE_FORMAT.parse(DATE_FORMAT.format(date ?: Calendar.getInstance().time))
//        val nextDay = Date(currentDay!!.time + MILLIS_IN_A_DAY)
//        val listTaskByDay = listTasks.filter {task ->
//            task.deadline >= currentDay.time && task.deadline <= nextDay.time
//        }
//        return listTaskByDay
//    }

}