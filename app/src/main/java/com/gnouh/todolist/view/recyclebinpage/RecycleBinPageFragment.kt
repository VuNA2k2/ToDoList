package com.gnouh.todolist.view.recyclebinpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnouh.todolist.databinding.FragmentRecycleBinPageBinding

class RecycleBinPageFragment : Fragment() {
    private lateinit var recycleBinPageBinding: FragmentRecycleBinPageBinding
    private lateinit var recycleBinPageViewModel: RecycleBinPageViewModel
    private lateinit var taskDeletedAdapter: TaskDeletedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recycleBinPageBinding = FragmentRecycleBinPageBinding.inflate(inflater, container, false)
        recycleBinPageViewModel = ViewModelProvider(this)[RecycleBinPageViewModel::class.java]
        taskDeletedAdapter = TaskDeletedAdapter(
            restore = {
                val task = it
                task.isDelete = false
                recycleBinPageViewModel.update(task)
                recycleBinPageViewModel.scheduleNotification(task)
            }, delete = {
                recycleBinPageViewModel.delete(it)
            }
        )
        recycleBinPageBinding.apply {
            val linearLayoutManager = LinearLayoutManager(context)
            linearLayoutManager.reverseLayout = true
            linearLayoutManager.stackFromEnd = true
            listDeletedTask.layoutManager = linearLayoutManager
            listDeletedTask.setHasFixedSize(true)
            listDeletedTask.adapter = taskDeletedAdapter
        }

        recycleBinPageViewModel.getTaskDel().observe(viewLifecycleOwner) {
            taskDeletedAdapter.data = it
            if (it.isNotEmpty()) {
                recycleBinPageBinding.imgEmptyAllTask.visibility = View.INVISIBLE
            } else {
                recycleBinPageBinding.imgEmptyAllTask.visibility = View.VISIBLE
            }
        }

        return recycleBinPageBinding.root
    }
}