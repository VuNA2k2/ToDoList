package com.gnouh.todolist.broadcast

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.gnouh.todolist.App
import com.gnouh.todolist.R
import com.gnouh.todolist.constants.DESCRIPTION_EXTRA
import com.gnouh.todolist.constants.NOTIFY_ID
import com.gnouh.todolist.constants.TITLE_EXTRA

class AlarmBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notification = NotificationCompat.Builder(context, App.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notify)
            .setContentTitle(intent.getStringExtra(TITLE_EXTRA))
            .setContentText(intent.getStringExtra(DESCRIPTION_EXTRA))
            .build()
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationID = intent.getIntExtra(NOTIFY_ID,1)
        manager.notify(notificationID, notification)
    }
}