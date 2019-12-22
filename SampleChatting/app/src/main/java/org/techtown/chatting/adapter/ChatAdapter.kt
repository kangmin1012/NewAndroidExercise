package org.techtown.chatting.adapter

import android.content.Context
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.chatting.R
import org.techtown.chatting.data.ChatData
import org.techtown.chatting.viewHolder.ChatAnotherViewHolder
import org.techtown.chatting.viewHolder.ChatMyViewHolder

class ChatAdapter(private val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var data = arrayListOf<ChatData>()

    private val MY_CHAT = 0
    private val ANOTHER_CHAT = 1

    override fun getItemViewType(position: Int): Int {

        if (data[position].id == "me"){
            return MY_CHAT
        }
        else{
            return ANOTHER_CHAT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == MY_CHAT){
            val myview = LayoutInflater.from(context).inflate(R.layout.my_chat_item, parent, false)
            return ChatMyViewHolder(myview)
        }
        else{
            val otherview = LayoutInflater.from(context).inflate(R.layout.other_chat_item, parent, false)
            return ChatAnotherViewHolder(otherview)
        }

    }

    override fun getItemCount(): Int {
        Log.d("ok","ItemCountok")
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
             is ChatMyViewHolder -> {
                holder.onBind(data[position])
                 Log.d("ok","Bindok")
            }
            is ChatAnotherViewHolder ->{
                holder.onBind(data[position])
                Log.d("ok","Bindok")
            }
        }
    }

    fun addItem(item : ChatData){
        data.add(item)
    }
}