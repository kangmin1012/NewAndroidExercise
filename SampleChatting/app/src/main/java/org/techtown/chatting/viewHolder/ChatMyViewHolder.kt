package org.techtown.chatting.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.techtown.chatting.R
import org.techtown.chatting.data.ChatData

class ChatMyViewHolder (view : View) : RecyclerView.ViewHolder(view){
    var message : TextView = view.findViewById(R.id.tv_chatmyitem_contents)

    fun onBind(chatData: ChatData){
        message.text = chatData.message
    }
}