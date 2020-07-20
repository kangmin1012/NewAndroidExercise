package com.example.swiperefresh

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RcvAdapter (private val context : Context) : RecyclerView.Adapter<RcvViewHolder>(){

    var data = arrayListOf<RcvData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcvViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.rcv_item, parent, false)
        return RcvViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RcvViewHolder, position: Int) {
        holder.onBind(data[position])
    }
}