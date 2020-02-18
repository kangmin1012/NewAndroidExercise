package org.techtown.sampleinternal

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImgVH(view : View) : RecyclerView.ViewHolder(view){
    val img : ImageView = view.findViewById(R.id.rcv_img)

    fun bind(data : String){
        Glide.with(itemView)
            .load(data)
            .into(img)
    }
}