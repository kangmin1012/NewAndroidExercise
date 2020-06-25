package com.example.sampledaum

import android.graphics.drawable.AnimatedVectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_img.setImageResource(R.drawable.anim_dia_to_clock)

        main_img.setOnClickListener {
            if (clickCount == 0 ) {
                clickCount += 1
            }
            else {
                main_img.setImageResource(R.drawable.anim_clock_to_star)
            }
            (main_img.drawable as AnimatedVectorDrawable).start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DestroyCall", "Call Destroy")

    }
}