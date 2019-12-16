package com.example.androidproj.ui.fragment


import android.Manifest
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import android.os.Environment.MEDIA_MOUNTED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.androidproj.R
import kotlinx.android.synthetic.main.fragment_storage.*
import java.io.File


class StorageFragment : Fragment() {


    lateinit var sp: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_storage, container, false)
    }

    private fun requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        ) {
            Toast.makeText(
                activity,
                "Write External Storage permission allows us to create files. Please allow this permission in App Settings.",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                activity!!,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requestPermission()



//        sp = context?.getSharedPreferences(
//            "STORAGE_ONE",
//            Context.MODE_PRIVATE
//        )!!
//
//
//        if (sp.contains("KEY")) {
//            stored_text.text = sp.getString("KEY", "none")
//        }

        val filePath = context?.getExternalFilesDir(null)
        val newFolder = "new_folder"
        val fileName = "newfile.txt"

        val folder = File(filePath, newFolder)
        if (folder.exists()) {
            val file = File(folder, fileName)
            if (file.exists()) {
                val content = file.readText(Charsets.UTF_8)
                stored_text.text = content
            }
        }

        save.setOnClickListener {
            saveFile()
        }
    }

    private fun saveSp() {

        val editor = sp.edit()
        editor?.putString("KEY", text.text.toString())
        editor?.apply()
    }


    private fun saveFile() {

        if (Environment.getExternalStorageState() == MEDIA_MOUNTED) {

            val filePath = Environment.getExternalStorageDirectory()
            val newFolder = "new_folder"
            val fileName = "newfile.txt"

            val folder = File(filePath?.absolutePath, newFolder)
            if (!folder.exists()) {
                folder.mkdir()
            }

            val file = File(folder, fileName)

            if (!file.exists()) {
                file.createNewFile()
            }

            file.length()
            file.writeText(text.text.toString(), Charsets.UTF_8)


//        context?.openFileOutput(fileName, Context.MODE_APPEND).use {
//            it?.write(text.text.toString().toByteArray(Charsets.UTF_8))
//            it?.write("\n".toByteArray(Charsets.UTF_8))
//        }

            //file.length()
        }

    }


}