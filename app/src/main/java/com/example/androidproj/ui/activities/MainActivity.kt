package com.example.androidproj.ui.activities


import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidproj.R
import com.example.androidproj.TmsService
import com.example.androidproj.logDebug
import com.example.androidproj.repository.RemoteRepository
import com.example.androidproj.repository.network.NetworkHelper
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: MainViewModel

    private var tmsService: TmsService? = null

    override fun onStart() {
        super.onStart()
        logDebug("onStart")


        viewModel = ViewModelProviders
            .of(this, MainViewModelFactory(application, RemoteRepository()))
            .get(MainViewModel::class.java)


        viewModel.data.observe(this, observer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logDebug("onCreate")

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, ListFragment())
//            .commit()

        getData()

    }

    fun getData() {

//        NetworkHelper.getApi().getNetworkData(3)
//            .enqueue(object : Callback<Entity>{
//                override fun onFailure(call: Call<Entity>, t: Throwable) {
//
//                }
//
//                override fun onResponse(call: Call<Entity>, response: Response<Entity>) {
//                    val objects: Entity =  response.body() as Entity
//                }
//            })


        NetworkHelper.getApi().getNetworkData2("json","QWASscmom6ArXoInDL4TOao_AIW5zYmb4cJj")
            .enqueue(object: Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {

                }
            })




    }

    private val serviceConnection = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {
            name?.toString()
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as TmsService.LocalBinder
            tmsService = binder.service
            tmsService?.todo()
        }
    }

    fun click(view: View) {

    }


    override fun onClick(v: View?) {
        when (v?.id) {

            R.id.button -> {
                try {
                    unbindService(serviceConnection)
                } catch (e: Exception) {
                    e.message?.let {
                        logDebug(it)
                    }
                }
            }

            R.id.text -> {
                button.setText(R.string.hello)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        logDebug("onResume")
    }

    override fun onPause() {
        super.onPause()
        logDebug("onPause")
    }

    override fun onStop() {
        super.onStop()
        logDebug("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        logDebug("onDestroy")
        //stopService()
    }

    private val observer = Observer<String> {
        text.text = it
    }
}