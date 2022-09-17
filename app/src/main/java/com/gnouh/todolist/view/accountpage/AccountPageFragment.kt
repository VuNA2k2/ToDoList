package com.gnouh.todolist.view.accountpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnouh.todolist.databinding.FragmentAccountPageBinding

class AccountPageFragment : Fragment() {
    private lateinit var accountPageBinding: FragmentAccountPageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountPageBinding = FragmentAccountPageBinding.inflate(inflater, container, false)
        return accountPageBinding.root
    }
}