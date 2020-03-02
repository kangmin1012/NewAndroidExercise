package org.techtown.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_sample.*

class sampleActivity : AppCompatActivity() {

    var find : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)

        val listAdp : ListAdapter = ListAdapter(this)
        rcv_sample.adapter = listAdp
        rcv_sample.layoutManager = LinearLayoutManager(this)


        listAdp.data = mutableListOf(
            ListData(profileName = "이형준"),
            ListData(profileName = "강민구"),
            ListData(profileName = "강민구"),
            ListData(profileName = "강민구"),
            ListData(profileName = "강민구"),
            ListData(profileName = "강민구"),
            ListData(profileName = "강민구"),
            ListData(profileName = "강민구")
        )

        listAdp.notifyDataSetChanged()
    }
}
