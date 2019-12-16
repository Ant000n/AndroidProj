package com.example.androidproj.ui.activities.list


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Data(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name="type") var type: String,
    @ColumnInfo(name = "checked") var checked: Boolean = false

)