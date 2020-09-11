package org.sample.motionlayoutsample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val handler = Handler(mainLooper)

        handler.postDelayed(Runnable {
            run {
                imageView.callOnClick()
            }
        },2000)

        handler.postDelayed(Runnable {
            run{
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_fade, R.anim.slide_hold)
            }
        },2600)




    }
}