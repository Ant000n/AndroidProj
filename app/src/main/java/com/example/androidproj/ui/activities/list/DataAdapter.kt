package com.example.androidproj.ui.activities.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidproj.R
import kotlinx.android.synthetic.main.item_data.view.*

class DataAdapter(
    private val callback: DataCallback?
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    var list = arrayListOf<Data>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            callback?.itemClicked(list[holder.adapterPosition])
        }
    }

    override fun getItemCount() = list.size

    inner class DataViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val textId = itemView.item_id
        private val textTitle = itemView.item_title
        private val checkBox = itemView.checkbox

        fun bind(data: Data) {
            textId.text = data.id.toString()
            textTitle.text = data.title
            textTitle.setOnClickListener {
                callback?.itemClicked(data)
            }



            checkBox.setOnCheckedChangeListener { _, isChecked ->
                callback?.itemChecked(data)
                //data.checked = isChecked
            }
            checkBox.isChecked = data.checked
        }
    }

    interface DataCallback {
        fun itemClicked(item: Data)
        fun itemChecked(item: Data)
    }

}