package com.example.androidproj.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.androidproj.R
import com.example.androidproj.repository.RemoteRepository
import kotlinx.android.synthetic.main.fragment_async.*

class AsyncFragment : Fragment() {

    lateinit var viewModel: AsyncViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_async, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AsyncViewModel::class.java)

        viewModel.newText.observe(viewLifecycleOwner, Observer {
            button_load.text = it
            progress.visibility = View.INVISIBLE
            button_load.visibility = View.VISIBLE

        })

        button_load.setOnClickListener {
            progress.visibility = View.VISIBLE
            viewModel.load()
            //loadData()
        }
    }


    private fun loadData() {
        val thread = Thread(Runnable {
            val repo = RemoteRepository()
            val text = repo.getData()
            //newText.postValue(text)
            activity?.runOnUiThread {
                button_load.text = text
                progress.visibility = View.INVISIBLE
            }
        })

        thread.start()
    }
}