package com.example.sampledaum.rcv

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sampledaum.R

class ProductAdapter (val context : Context) : RecyclerView.Adapter<ProductVH>(){

    var data = arrayListOf<ProductData>()

    interface OnItemClickListener{
        fun onItemClick(v : View, data : ProductData)
    }

    private var listener : OnItemClickListener? = null

    fun setOnItemClickListener (listener : OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductVH {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_rcv_item,parent,false)
        return ProductVH(view)

    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProductVH, position: Int) {
        holder.onBind(data[position],listener)
    }

    fun onAddItem(item : ProductData){
        data.add(item)
//        notifyItemInserted(data.size - 1)

    }

}