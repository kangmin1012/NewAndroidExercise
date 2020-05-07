package org.techtown.mingmangchat.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.techtown.mingmangchat.R
import org.techtown.mingmangchat.VH.ChatAnotherViewHolder
import org.techtown.mingmangchat.VH.ChatMyViewHolder
import org.techtown.mingmangchat.data.ChatData

class ChatAdapter(private val context: Context, var NICKNAME : String) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    var data = arrayListOf<ChatData?>()

    private val MY_CHAT = 0
    private val ANOTHER_CHAT = 1

    override fun getItemViewType(position: Int): Int {

        if (data[position]?.id == NICKNAME){
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

    override fun getItemCount(): Int = data?.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ChatMyViewHolder -> {
                holder.onBind(data[position])
            }
            is ChatAnotherViewHolder ->{
                holder.onBind(data[position])
            }
        }
    }

    fun addItem(item : ChatData?){
        data.add(item)
        notifyItemInserted(data?.size -1)
    }
}