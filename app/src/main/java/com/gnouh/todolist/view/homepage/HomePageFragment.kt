package com.gnouh.todolist.view.homepage

import android.R
import android.content.DialogInterface.OnShowListener
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnouh.todolist.databinding.BottomSheetAddEditBinding
import com.gnouh.todolist.databinding.FragmentHomePageBinding
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

    private fun showModalBottomSheet(handelEvent: HandelEvent) {
        val bottomSheetAddEditBinding = BottomSheetAddEditBinding.inflate(layoutInflater)

        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(bottomSheetAddEditBinding.root)

        bottomSheetDialog.show()
    }


}