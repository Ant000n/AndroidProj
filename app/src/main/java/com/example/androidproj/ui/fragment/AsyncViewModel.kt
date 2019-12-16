package com.example.androidproj.ui.fragment


import android.os.Handler
import android.os.HandlerThread
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidproj.repository.RemoteRepository
import com.example.androidproj.repository.Repository
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class AsyncViewModel : ViewModel() {


    val newText = MutableLiveData<String>()

    private val handlerThread: HandlerThread = HandlerThread("HandlerThread")
    private val handlerThread2: HandlerThread = HandlerThread("HandlerThread2")
    private val handlerMain2: Handler;
    private var handler: Handler
    private var mainThreadHandler: Handler


    init {
        handlerThread.start()
        handlerThread2.start()
        handler = Handler(handlerThread.looper)
        handlerMain2 = Handler(Handler.Callback {
            return@Callback true
        })

        mainThreadHandler = Handler(handlerThread2.looper, Handler.Callback {
            when (it.what) {
                100 -> {
                    Log.d("AsyncViewModel", "onSuccess")
                    newText.postValue(it.obj as String)
                }
            }
            return@Callback true
        })
    }

    private val repo: Repository = RemoteRepository()

    fun load() {
        handler.post {
            val text = repo.getData()
            val message = mainThreadHandler.obtainMessage(
                100,
                text
            )
            mainThreadHandler.sendMessage(message)
        }

        Log.d("AsyncViewModel", "next action")

    }


    fun loadRx() {

//        Single.create<String> {
//            it.onSuccess(repo.getData())
//        }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<String> {
//                override fun onSuccess(t: String) {
//                    newText.value = t
//                }
//
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onError(e: Throwable) {
//
//                }
//            })


        val single = Single.create<String> {

            val result = repo.getData()

            if (result.contains("H")) {
                it.onSuccess(result)
            } else {
                it.onError(IllegalArgumentException())
            }
        }

        val singleObserver = object : SingleObserver<String> {
            override fun onSuccess(t: String) {
                newText.value = t
                Log.d("AsyncViewModel", "onSuccess")
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {

            }
        }
    }
}