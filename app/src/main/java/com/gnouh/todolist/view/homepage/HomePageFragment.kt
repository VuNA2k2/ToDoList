package com.gnouh.todolist.view.homepage

import android.R
import android.annotation.SuppressLint
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
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
//                val picker = TimePicker(requireContext()).
            }

            btnAddOrEdit.setOnClickListener {
                taskViewModel.insert(Task(title = "Task from calendar", description = "Description", deadline = Calendar.getInstance().time.time))
                bottomSheetDialog.dismiss()
            }
        }
    }


}