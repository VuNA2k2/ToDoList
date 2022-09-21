package com.gnouh.todolist.view.homepage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnouh.todolist.databinding.FragmentHomePageBinding
import com.gnouh.todolist.models.Task
import com.gnouh.todolist.view.bottomsheetdialogaddoredit.AddOrEditBottomSheetDialogFragment


enum class HandleEvent {
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
        taskAllAdapter = TaskAllAdapter(
            delete = {
                val task = it
                task.isDelete = true
                taskViewModel.update(task)
            },
            update = {
                showModalBottomSheet(handleEvent = HandleEvent.EDIT, task = it)
            }
        )

        fragmentHomePageBinding.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            listAllTask.layoutManager = linearLayoutManager
            listAllTask.setHasFixedSize(true)
            listAllTask.adapter = taskAllAdapter
            fabAdd.setOnClickListener {
                showModalBottomSheet(handleEvent = HandleEvent.ADD)
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
    private fun showModalBottomSheet(handleEvent: HandleEvent, task: Task? = null) {
        if (handleEvent == HandleEvent.ADD) AddOrEditBottomSheetDialogFragment(task).show(parentFragmentManager, handleEvent.name)
        else AddOrEditBottomSheetDialogFragment(task).show(parentFragmentManager, handleEvent.name)
    }


}