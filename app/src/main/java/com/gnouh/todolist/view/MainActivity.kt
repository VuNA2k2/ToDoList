package com.gnouh.todolist.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import com.gnouh.todolist.R
import com.gnouh.todolist.databinding.ActivityMainBinding
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val navHost = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHost
        val navController = navHost.navController

        binding.bottomNav.setupWithNavController(navController)

//        val bottomBarConfig = AppBarConfiguration(
//            setOf(
//                R.id.calendarPageFragment, R.id.recycleBinPageFragment, R.id.homePageFragment, R.id.notifyPageFragment, R.id.accountPageFragment
//            )
//        )

//        setupActionBarWithNavController(navController, bottomBarConfig)
        setContentView(binding.root)
    }
}