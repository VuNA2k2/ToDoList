package com.gnouh.todolist.view.homepage

import android.R
import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.FrameLayout
import android.widget.TimePicker
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.constants.TIME_FORMAT
import com.gnouh.todolist.databinding.BottomSheetAddEditBinding
import com.gnouh.todolist.databinding.FragmentHomePageBinding
import com.gnouh.todolist.models.Task
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


enum class HandelEvent {
    ADD,
    EDIT
}

class HomePageFragment : Fragment() {
    private lateinit var fragmentHomePageBinding: FragmentHomePageBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAllAdapter: TaskAllAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomePageBinding = FragmentHomePageBinding.inflate(inflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskAllAdapter = TaskAllAdapter {
            val task = it
            task.isDelete = true
            taskViewModel.update(task)
        }

        fragmentHomePageBinding.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            listAllTask.layoutManager = linearLayoutManager
            listAllTask.setHasFixedSize(true)
            listAllTask.adapter = taskAllAdapter
            fabAdd.setOnClickListener {
                showModalBottomSheet(HandelEvent.ADD)
            }
        }

        taskViewModel.getAllTask().observe(viewLifecycleOwner) {
            taskAllAdapter.data = it
            if (it.isNotEmpty()) {
                fragmentHomePageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
            } else {
                fragmentHomePageBinding.imgEmptyAllTask.visibility = View.VISIBLE
            }
        }
        return fragmentHomePageBinding.root
    }

    @SuppressLint("SetTextI18n")
    private fun showModalBottomSheet(handelEvent: HandelEvent) {
        val bottomSheetAddEditBinding = BottomSheetAddEditBinding.inflate(layoutInflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.window?.let { WindowCompat.setDecorFitsSystemWindows(it, false) }
        bottomSheetDialog.setContentView(bottomSheetAddEditBinding.root)
        bottomSheetDialog.show()
        bottomSheetAddEditBinding.apply {
            val selectTime = MutableLiveData(Calendar.getInstance().time)

            selectTime.observe(viewLifecycleOwner) {
                tvDateTime.text = "${TIME_FORMAT.format(it)} ${DATE_FORMAT.format(it)}"
            }

            btnDateTimePicker.setOnClickListener {
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        val datePicker = DatePickerDialog(
                            requireContext(),
                            { _, year, month, dayOfMonth ->
//                                val timePicked = String.format("%02d:%02d", hourOfDay, minute)
//                                val datePicked =
//                                    String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                                selectTime.value = taskViewModel.createDate(hourOfDay, minute, dayOfMonth, month, year)
                            },
                            Calendar.getInstance().get(Calendar.YEAR),
                            Calendar.getInstance().get(Calendar.MONTH),
                            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis - 1000
                        datePicker.show()
                    },
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
                    Calendar.getInstance().get(Calendar.MINUTE),
                    true
                ).show()
            }

            btnAddOrEdit.setOnClickListener {
                taskViewModel.insert(
                    Task(
                        title = edtTitle.text.toString(),
                        description = edtDescription.text.toString(),
                        deadline = selectTime.value?.time ?: Calendar.getInstance().time.time
                    )
                )
                bottomSheetDialog.dismiss()
            }
        }
    }


}