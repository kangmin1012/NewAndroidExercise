package com.example.sampledaum.rcv

import android.os.SystemClock
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampledaum.R

class ProductVH (view : View) : RecyclerView.ViewHolder(view){

    val productImg : ImageView = view.findViewById(R.id.product_img)
    val productTitle : TextView = view.findViewById(R.id.product_txt)
    private var mLastClickTime : Long = 0

    fun onBind(
        data: ProductData,
        listener: ProductAdapter.OnItemClickListener?
    ){

        Glide.with(itemView).load(data.productImg)
            .error(R.drawable.ic_baseline_close_24)
            .into(productImg)
        productTitle.text = data.productTitle


        itemView.setOnClickListener {
            if(SystemClock.elapsedRealtime() - mLastClickTime > 1000){
                val pos = adapterPosition
                if ( pos != RecyclerView.NO_POSITION){
                    listener?.onItemClick(itemView, data)
                }
            }
            mLastClickTime = SystemClock.elapsedRealtime()
        }

    }
}