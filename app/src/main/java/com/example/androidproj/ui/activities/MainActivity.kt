package com.example.androidproj.ui.activities


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.*
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
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
import com.example.androidproj.repository.network.NewUserRequest
import com.example.androidproj.repository.network.UserListResponse
import com.example.androidproj.ui.AlarmReceiver
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), View.OnClickListener, SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var mLight: Sensor? = null

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

    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        logDebug("onCreate")

        //startService(Intent(this, TmsService::class.java))

//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.fragment_container, NotificationsFragment())
//            .commit()

        getData()


        val pedingIntent = PendingIntent.getService(
            this,
            0,
            Intent(this, AlarmReceiver::class.java),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        val am = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        am.setRepeating(
            AlarmManager.RTC_WAKEUP,
            0,
            60000,
            pedingIntent
        )

        var sensorManager: SensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener( this, mLight, SensorManager.SENSOR_DELAY_NORMAL)
    }
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //logDebug()
    }

    override fun onSensorChanged(event: SensorEvent) {
        logDebug(event.values[0].toString())
    }

    fun post() {
        val accessToken = "Authorization VBoALh0IUiCXleTsvpp8MqP6b2nquhIBCJRq"
        val newUser = NewUserRequest(
            "Katti",
            "Nick",
            "male",
            "john1@smith.com45",
            "active"
        )

        NetworkHelper.getApi().createNewUser(
            accessToken,
            newUser
        ).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            }
        })
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


        NetworkHelper.getApi().getNetworkData2(
            "json",
            "VBoALh0IUiCXleTsvpp8MqP6b2nquhIBCJRq\n", "john1@smith.com45"
        )
            .enqueue(object : Callback<UserListResponse> {
                override fun onFailure(call: Call<UserListResponse>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<UserListResponse>,
                    response: Response<UserListResponse>
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

//            R.id.text -> {
//                button.setText(R.string.hello)
//            }
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