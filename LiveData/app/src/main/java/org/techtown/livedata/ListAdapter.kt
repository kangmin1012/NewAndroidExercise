package org.techtown.livedata

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter (private val context: Context) : RecyclerView.Adapter<ListVH>(){

    var data = mutableListOf<ListData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVH {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)

        return ListVH(view)
    }

    override fun getItemCount(): Int = data.size


    override fun onBindViewHolder(holder: ListVH, position: Int) {
        holder.onBind(data[position])
    }
}