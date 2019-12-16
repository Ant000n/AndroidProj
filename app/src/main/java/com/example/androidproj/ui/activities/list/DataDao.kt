package com.example.androidproj.ui.activities.list


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DataDao {

    @Query("SELECT * FROM data")
    fun getAll(): List<Data>

    @Insert
    fun insert(vararg data: Data)

    @Insert
    fun insert(data: List<Data>)

    @Delete
    fun delete(data: Data)

    @Query("DELETE FROM data")
    fun deleteAll()

    @Query("SELECT * FROM data WHERE title IN (:title)")
    fun getDataByTitle(title: String): Data


}