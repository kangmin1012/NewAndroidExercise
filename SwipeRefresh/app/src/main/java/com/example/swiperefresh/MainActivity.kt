package com.example.swiperefresh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter : RcvAdapter
    private var count = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = RcvAdapter(this)

        rcv_main.apply {
            this.adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        for (i in 1 .. count){
            Log.d("count","$i")
            mAdapter.data.add(RcvData("$i"))
        }


        mAdapter.notifyDataSetChanged()

        swipe_refresh.setOnRefreshListener {
            for(i in count+1 .. count + 10){
                mAdapter.data.add(RcvData("$i"))
            }
            mAdapter.notifyDataSetChanged()
            count += 10

            swipe_refresh.isRefreshing = false
        }


    }
}