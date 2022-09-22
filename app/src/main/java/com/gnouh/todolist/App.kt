package com.gnouh.todolist

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import com.gnouh.todolist.constants.DESCRIPTION_EXTRA
import com.gnouh.todolist.constants.NOTIFY_ID

class App : Application(){
    companion object {
        const val CHANNEL_ID = "CHANNEL_TASK_MANAGER"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = NOTIFY_ID
        val description = DESCRIPTION_EXTRA
        val channel = NotificationChannel(App.CHANNEL_ID, name, NotificationManager.IMPORTANCE_HIGH)
        channel.description = description
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}