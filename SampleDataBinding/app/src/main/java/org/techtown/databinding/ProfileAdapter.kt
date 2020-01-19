package org.techtown.databinding

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.techtown.databinding.databinding.RcvListItemBinding

class ProfileAdapter (private val context : Context) : RecyclerView.Adapter<ProfileAdapter.ProfileVH>(){

    var data = listOf<ProfileData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileVH {
       val view = LayoutInflater.from(context).inflate(R.layout.rcv_list_item,parent,
            false)

        return ProfileVH(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProfileVH, position: Int) {
        holder.onBind(data[position])
    }

    class ProfileVH(view : View) : RecyclerView.ViewHolder(view){
        var binding : RcvListItemBinding? = DataBindingUtil.bind(view)

        fun onBind(data : ProfileData){
            binding?.user = data
        }

    }
}