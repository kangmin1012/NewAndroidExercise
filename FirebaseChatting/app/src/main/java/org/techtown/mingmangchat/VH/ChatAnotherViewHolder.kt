package org.techtown.mingmangchat.VH

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import org.techtown.mingmangchat.R
import org.techtown.mingmangchat.data.ChatData

class ChatAnotherViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var profileImage : ImageView = view.findViewById(R.id.iv_yourchatitem_profile)
    var user : TextView = view.findViewById(R.id.tc_yourchatitem_user)
    var message : TextView = view.findViewById(R.id.tv_yourchatitem_message)

    fun onBind(chatData: ChatData?){
        message.text = chatData?.message
        user.text = chatData?.user

//        Glide.with(itemView.context).load(chatData.profile)
//            .transform(RoundedCorners(180))
//            .transition(DrawableTransitionOptions.withCrossFade()) to profileImage

        profileImage.setOnClickListener {

        }
    }

}