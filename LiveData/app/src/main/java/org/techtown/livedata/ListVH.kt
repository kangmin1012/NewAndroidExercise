package org.techtown.livedata

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListVH (view : View) : RecyclerView.ViewHolder(view){

    val name : TextView = view.findViewById(R.id.Profile_name)
    val image : ImageView = view.findViewById(R.id.Profile_img)

    fun onBind(data : ListData){
        name.text = data.profileName
    }

}