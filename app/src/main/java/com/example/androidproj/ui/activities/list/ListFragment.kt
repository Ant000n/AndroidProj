package com.example.androidproj.ui.activities.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.androidproj.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataAdapter = DataAdapter(null)
        recycler_view.adapter = dataAdapter
        recycler_view.layoutManager =
            LinearLayoutManager(context!!, LinearLayoutManager.VERTICAL, false)

        val db = Room.databaseBuilder(
            context?.applicationContext!!,
            AppDatabase::class.java,
            "name"
        )
            .allowMainThreadQueries()
            .build()


        val data0 = Data(0, "Data0", "type0")

        //db.getDataDatabase().insert(data0)
        //db.getDataDatabase().deleteAll()
        //val dataList = db.getDataDatabase().getAll()
        //db.getDataDatabase().delete(data0)
        //val dataList2 = db.getDataDatabase().getAll()

        //dataAdapter.list.addAll(dataList)
        db.getDataDatabase().insert(getTestData())
        val dataList = db.getDataDatabase().getAll()
        val data = db.getDataDatabase().getDataByTitle("title 1")
        dataAdapter.notifyDataSetChanged()
    }


//    private val callback = object : DataAdapter.DataCallback {
//        override fun itemClicked(item: Data) {
//            item_text.text = item.id.toString()
//        }
//    }


    private fun getTestData(): ArrayList<Data> {

        val listData = arrayListOf<Data>()
        for (i in 1..100) {

            val data = Data(i, "title $i", "")
            listData.add(data)
        }

        return listData
    }


}