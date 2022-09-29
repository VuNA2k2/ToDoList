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
import com.gnouh.todolist.R
import com.gnouh.todolist.constants.DATE_FORMAT
import com.gnouh.todolist.constants.TIME_FORMAT
import com.gnouh.todolist.models.Task
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import com.gnouh.todolist.databinding.BottomSheetAddEditBinding

class AddOrEditBottomSheetDialogFragment(val task: Task?) : BottomSheetDialogFragment() {

    private lateinit var bottomSheetAddEditBinding: BottomSheetAddEditBinding
    private lateinit var bottomSheetViewModel: BottomSheetViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomSheetAddEditBinding = BottomSheetAddEditBinding.inflate(inflater, container, false)
        bottomSheetViewModel = ViewModelProvider(this)[BottomSheetViewModel::class.java]
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
                btnAddOrEdit.setImageResource(R.drawable.ic_save)
                tvTimeUpdate.setText(R.string.update_history)
                tvTimeUpdate.text = "${tvTimeUpdate.text} ${TIME_FORMAT.format(Date(it.timeCreate))} ${DATE_FORMAT.format(Date(it.timeCreate))}"
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
                                selectTime.value = bottomSheetViewModel.createDate(
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
                    val tmpTask = Task(
                        title = edtTitle.text.toString(),
                        description = edtDescription.text.toString(),
                        deadline = selectTime.value?.time ?: Calendar.getInstance().time.time
                    )
                    bottomSheetViewModel.insert(
                        tmpTask
                    )
                    bottomSheetViewModel.scheduleNotification(tmpTask)
                } else {
                    task.title = edtTitle.text.toString()
                    task.description = edtDescription.text.toString()
                    task.deadline = selectTime.value?.time ?: Calendar.getInstance().time.time
                    task.timeCreate = Calendar.getInstance().timeInMillis
                    bottomSheetViewModel.update(task)
                    bottomSheetViewModel.scheduleNotification(task)
                }
                dialog?.dismiss()
            }
        }
    }
}