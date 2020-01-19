package org.techtown.databinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.techtown.databinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var profileAdapter : ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.activity = this@MainActivity
        setRcv()
    }

    fun btnClick(view : View){
        Toast.makeText(this,"Button Click",Toast.LENGTH_SHORT).show()
    }

    fun setRcv(){
        binding.mainRcv.layoutManager = LinearLayoutManager(this)
        binding.mainRcv.adapter = profileAdapter
        profileAdapter.data = listOf(
            ProfileData(profile = "", name = "Kang", age = 26),
            ProfileData(profile = "",name = "Kim", age = 25)
        )
        profileAdapter.notifyDataSetChanged()
    }

}
