package com.example.androidproj


import android.app.IntentService
import android.content.Intent
import android.os.Binder
import android.os.IBinder


class TmsService : IntentService("TmsService") {

    private val mBinder: IBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        val service: TmsService = this@TmsService
    }

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    override fun onHandleIntent(intent: Intent?) {
        logDebug(Thread.currentThread().name)
        logDebug("onHandleIntent")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logDebug("onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onCreate() {
        super.onCreate()
        logDebug(Thread.currentThread().name)
        logDebug("onCreate")
    }

    override fun onDestroy() {
        super.onDestroy()
        logDebug("onDestroy")
    }


    fun todo() {
        logDebug("todo")
    }
}