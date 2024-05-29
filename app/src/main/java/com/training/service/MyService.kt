package com.training.service

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.widget.Toast

class MyService : Service() {
    val SERVICE_ID = 1
    override fun onBind(p0: Intent?): IBinder? {
     return null
    }

    @SuppressLint("ForegroundServiceType")
    override fun onCreate() {
        val CHANNEL_ID = "MyServiceChannel"
        val channal =NotificationChannel(CHANNEL_ID,"Default",NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channal)
        val notification = Notification.Builder(this, CHANNEL_ID).apply {
            setContentTitle("My Service")
            setContentText("Running...")
            setSmallIcon(R.drawable.ic_launcher_background)
        }.build()
        startForeground(SERVICE_ID, notification)
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        showMessage()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun showMessage() {
        Handler(mainLooper).postDelayed(
            {
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show()
            },
            5000
        )
    }
}