@file:Suppress("UNCHECKED_CAST")

package com.example.samplelayoutani

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.util.Pair
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        main_card.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)

            var options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                this,
                Pair.create(name_txt, "nameTransition"),
                Pair.create(content_txt, "contentTransition"),
                Pair.create(profile_img, "imageTransition")
            )
            startActivity(intent, options.toBundle())
        }


    }
}