package com.gnouh.todolist.view.recyclebinpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnouh.todolist.databinding.FragmentRecycleBinPageBinding
import com.gnouh.todolist.view.homepage.TaskAllAdapter
import com.gnouh.todolist.view.homepage.TaskViewModel

class RecycleBinPageFragment : Fragment() {
    private lateinit var recycleBinPageBinding: FragmentRecycleBinPageBinding
    private lateinit var taskViewModel: TaskViewModel
    private lateinit var taskAllAdapter: TaskAllAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recycleBinPageBinding = FragmentRecycleBinPageBinding.inflate(inflater, container, false)
        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskAllAdapter = TaskAllAdapter {
            val task = it
            task.isDelete = false
            taskViewModel.update(task)
        }
        recycleBinPageBinding.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            listDeletedTask.layoutManager = linearLayoutManager
            listDeletedTask.setHasFixedSize(true)
            listDeletedTask.adapter = taskAllAdapter
        }

        taskViewModel.getTaskDel().observe(viewLifecycleOwner) {
            taskAllAdapter.data = it
            if (it.isNotEmpty()) {
                recycleBinPageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
            } else {
                recycleBinPageBinding.imgEmptyAllTask.visibility = View.VISIBLE
            }
        }

        return recycleBinPageBinding.root
    }
}