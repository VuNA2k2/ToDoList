package com.gnouh.todolist.view.notifypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnouh.todolist.databinding.FragmentNotifyPageBinding

class NotifyPageFragment : Fragment() {
    private lateinit var notifyPageBinding: FragmentNotifyPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notifyPageBinding = FragmentNotifyPageBinding.inflate(inflater, container, false)


        notifyPageBinding.apply {

        }

        return notifyPageBinding.root
    }
}