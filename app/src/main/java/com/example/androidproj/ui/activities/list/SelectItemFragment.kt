package com.example.androidproj.ui.activities.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproj.R
import kotlinx.android.synthetic.main.fragment_list.recycler_view
import kotlinx.android.synthetic.main.fragment_selected_item.*

class SelectItemFragment : Fragment() {

    private lateinit var listAdapter: DataAdapter
    private var selectedItem: Data? = null
    private lateinit var dataList: ArrayList<Data>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_selected_item, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = DataAdapter(object : DataAdapter.DataCallback {

            override fun itemClicked(item: Data) {
                selectedItem = item
                et_id.setText(item.id.toString())
                et_title.setText(item.title)
            }

            override fun itemChecked(item: Data) {
//                val pos = dataList.indexOf(item)
//                dataList[pos].checked = !item.checked
            }
        })

        dataList = getTestData()

        context?.let {
            recycler_view.apply {
                layoutManager = LinearLayoutManager(it, LinearLayoutManager.VERTICAL, false)
                adapter = listAdapter.apply {
                    list = dataList
                }
            }
        }

        button_add.setOnClickListener {
            val id = et_id.text.toString().toInt()
            val title = et_title.text.toString()
            val position = listAdapter.list.indexOf(selectedItem!!)

            listAdapter.list[position].apply {
                this.id = id
                this.title = title
            }

            listAdapter.notifyItemChanged(position)
            selectedItem = null
        }


        button_remove.setOnClickListener {
            val pos = dataList.indexOf(selectedItem)
            dataList.removeAt(pos)
            listAdapter.notifyItemRemoved(pos)
        }

    }

    private fun getTestData(): ArrayList<Data> {

        val listData = arrayListOf<Data>()
        for (i in 0..100) {

            val data = Data(i, "title $i", "")
            listData.add(data)
        }

        return listData
    }

}