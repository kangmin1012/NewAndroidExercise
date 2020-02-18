package org.techtown.sampleinternal

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ImgAdp (private val context : Context) : RecyclerView.Adapter<ImgVH>(){
    var data = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgVH {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_item,parent,false)

        return ImgVH(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ImgVH, position: Int) {
        holder.bind(data[position])
    }
}