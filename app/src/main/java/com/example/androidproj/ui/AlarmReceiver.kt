package com.example.androidproj.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.androidproj.logDebug

class AlarmReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        logDebug("LogDebug")
    }
}