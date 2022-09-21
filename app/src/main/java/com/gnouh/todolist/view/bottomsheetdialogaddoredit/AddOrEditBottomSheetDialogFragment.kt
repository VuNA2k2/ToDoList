package com.gnouh.todolist.view.bottomsheetdialogaddoredit

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.constants.TIME_FORMAT
import com.gnouh.todolist.databinding.BottomSheetAddEditBinding
import com.gnouh.todolist.models.Task
import com.gnouh.todolist.view.homepage.TaskViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.util.*

class AddOrEditBottomSheetDialogFragment(val task: Task?) : BottomSheetDialogFragment() {

    private lateinit var bottomSheetAddEditBinding: BottomSheetAddEditBinding
    private lateinit var taskViewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomSheetAddEditBinding = BottomSheetAddEditBinding.inflate(inflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        return bottomSheetAddEditBinding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.let { WindowCompat.setDecorFitsSystemWindows(it, false) }
        bottomSheetAddEditBinding.apply {
            task?.let {
                edtTitle.setText(it.title)
                edtDescription.setText(it.description)
            }
            val selectTime = if (task != null) {
                MutableLiveData(Date(task.deadline))
            } else {
                MutableLiveData(Calendar.getInstance().time)
            }

            selectTime.observe(viewLifecycleOwner) {
                tvDateTime.text = "${TIME_FORMAT.format(it)} ${DATE_FORMAT.format(it)}"
            }

            btnDateTimePicker.setOnClickListener {
                val cal = Calendar.getInstance()
                cal.time = selectTime.value!!
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        val datePicker = DatePickerDialog(
                            requireContext(),
                            { _, year, month, dayOfMonth ->
//                                val timePicked = String.format("%02d:%02d", hourOfDay, minute)
//                                val datePicked =
//                                    String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year)
                                selectTime.value = taskViewModel.createDate(
                                    hourOfDay,
                                    minute,
                                    dayOfMonth,
                                    month,
                                    year
                                )
                            },
                            cal.get(Calendar.YEAR),
                            cal.get(Calendar.MONTH),
                            cal.get(Calendar.DAY_OF_MONTH)
                        )
                        datePicker.datePicker.minDate = Calendar.getInstance().timeInMillis - 1000
                        datePicker.show()
                    },
                    cal.get(Calendar.HOUR_OF_DAY),
                    cal.get(Calendar.MINUTE),
                    true
                ).show()
            }

            btnAddOrEdit.setOnClickListener {
                if (task == null) {
                    taskViewModel.insert(
                        Task(
                            title = edtTitle.text.toString(),
                            description = edtDescription.text.toString(),
                            deadline = selectTime.value?.time ?: Calendar.getInstance().time.time
                        )
                    )
                } else {
                    taskViewModel.update(task)
                }
                dialog?.dismiss()
            }
        }
    }
}