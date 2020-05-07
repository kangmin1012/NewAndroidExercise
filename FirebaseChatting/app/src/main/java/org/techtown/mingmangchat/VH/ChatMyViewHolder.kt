package org.techtown.mingmangchat.VH

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.mingmangchat.R
import org.techtown.mingmangchat.data.ChatData

class ChatMyViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var message : TextView = view.findViewById(R.id.tv_chatmyitem_contents)
    fun onBind(chatData: ChatData?){
        message.text = chatData?.message
    }
}