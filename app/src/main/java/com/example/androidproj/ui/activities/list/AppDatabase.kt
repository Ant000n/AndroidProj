package com.example.androidproj.ui.activities.list


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Data::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getDataDatabase(): DataDao

}