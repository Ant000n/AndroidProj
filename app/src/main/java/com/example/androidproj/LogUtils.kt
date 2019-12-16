package com.example.androidproj


import android.util.Log


fun Any.logDebug(message: String) {
    Log.d(this::class.java.name, message)
}