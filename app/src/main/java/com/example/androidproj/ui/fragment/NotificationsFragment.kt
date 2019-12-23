package com.example.androidproj.ui.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import com.example.androidproj.R
import kotlinx.android.synthetic.main.frogment_notification.*

class NotificationsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frogment_notification, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createNotifChannel()
        me_button.setOnClickListener {
            NotifyMe()
        }
        chanel_button?.setOnClickListener {
            val nm = NotificationManagerCompat.from(context!!)
            nm.cancel(100)
        }
    }


    private fun NotifyMe() {
        context?.let {
            val notif = NotificationCompat.Builder(it, "222")
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle("111111111111111111111111")
                .setSound(null)
                .setContentText("Оповещение")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()

            val nm = NotificationManagerCompat.from(it)

            nm.notify(
                100,
                notif
            )
        }
    }
    private fun createNotifChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chanel = NotificationChannel(
                "222",
            "Main chanel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "This in description"
                setSound(null, null)
            }
            NotificationManagerCompat.from(context!!).createNotificationChannel(chanel)

            val list = NotificationManagerCompat.from(context!!).notificationChannels
            list.size
        }
    }
}