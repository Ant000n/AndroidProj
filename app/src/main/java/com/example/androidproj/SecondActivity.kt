package com.example.androidproj


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {


    companion object {

        private const val KEY = "String"
        const val VALUE = "value"

        fun getIntent(context: Context) = Intent(context, SecondActivity::class.java)

        fun getIntent(context: Context, param: String): Intent {
            return getIntent(context).apply {
                putExtra(KEY, param)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        title = if (intent.hasExtra(KEY)) {
            intent.getStringExtra(KEY)
        } else {
            resources.getString(R.string.app_name)
        }


        button1.setOnClickListener {
            var integer = text2.text.toString().toInt()
            text2.text = (++integer).toString()
        }

        finish.setOnClickListener {
            setResult(Activity.RESULT_OK, intent.apply {
                putExtra(VALUE, text2.text)
            })
            finish()
        }

    }


}