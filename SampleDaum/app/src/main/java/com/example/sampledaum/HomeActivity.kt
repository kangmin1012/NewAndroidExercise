@file:Suppress("DEPRECATION")

package com.example.sampledaum


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val anim = AnimationUtils.loadAnimation(this,R.anim.show_text_anim)

        button_1.visibility = View.INVISIBLE
        button_2.visibility = View.INVISIBLE

        val handler = Handler()
        handler.postDelayed(
            {
             run{
                 button_1.visibility = View.VISIBLE
                 button_1.startAnimation(anim)
             }
        },500)

        val thread2 = Thread2()
        thread2.start()

        button_1.setOnClickListener {
            val intent = Intent(this, LayoutaniActivity::class.java)
            startActivity(intent)
        }

        button_2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }


    inner class Thread2: Thread(){

        override fun run() {
            val handler = Handler(Looper.getMainLooper())
            val anim = AnimationUtils.loadAnimation(this@HomeActivity, R.anim.show_text_anim)
            runOnUiThread {
                handler.postDelayed({
                    run{
                        button_2.visibility = View.VISIBLE
                        button_2.startAnimation(anim)
                    }
                },1000)

            }
        }
    }

}




