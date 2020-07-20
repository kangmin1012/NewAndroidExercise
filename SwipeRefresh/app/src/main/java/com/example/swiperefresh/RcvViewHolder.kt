package com.example.swiperefresh

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RcvViewHolder ( view : View) : RecyclerView.ViewHolder(view){

    private val textSample : TextView = view.findViewById(R.id.txt_sample)

    fun onBind(data : RcvData){
        textSample.text = data.text
    }
}