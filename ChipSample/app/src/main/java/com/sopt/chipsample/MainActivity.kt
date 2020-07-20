@file:Suppress("DEPRECATION")

package com.sopt.chipsample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sample_chip.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_init.setOnClickListener {
            if (edt_chip.text != null){
                val chip = Chip(this)
                val str = edt_chip.text.toString()
                chip.text = str
                chip.gravity = View.TEXT_ALIGNMENT_CENTER
                chip.setChipBackgroundColorResource(R.color.pink)
                chip.setOnClickListener {
                    group_chip.removeView(it)
                    chip.text
                }
                group_chip.addView(chip)
            }
        }

    }
}