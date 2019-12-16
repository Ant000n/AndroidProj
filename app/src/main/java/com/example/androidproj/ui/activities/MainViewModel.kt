package com.example.androidproj.ui.activities


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.androidproj.repository.Repository

class MainViewModel(
    application: Application,
    private val repo: Repository
) : AndroidViewModel(application) {

    val data = MutableLiveData<String>()


    fun getString() {
        data.value = repo.getData()
        //data.value = "1"
    }

}