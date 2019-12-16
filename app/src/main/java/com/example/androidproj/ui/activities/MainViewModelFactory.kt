package com.example.androidproj.ui.activities


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidproj.repository.Repository

class MainViewModelFactory(
    private val application: Application,
    private val repos: Repository
) : ViewModelProvider.AndroidViewModelFactory(application){


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass == MainViewModel::class.java) {
            return MainViewModel(application, repos) as T
        }
        throw IllegalArgumentException()
    }


}