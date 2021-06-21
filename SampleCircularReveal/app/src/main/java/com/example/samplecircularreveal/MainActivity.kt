package com.example.samplecircularreveal

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import com.example.samplecircularreveal.databinding.ActivityMainBinding
import kotlin.math.hypot

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.firstLayout.setOnClickListener {
            if (binding.secondLayout.visibility == View.INVISIBLE)
                startCircleReveal()
        }

        binding.secondLayout.setOnClickListener {
            if (binding.secondLayout.visibility == View.VISIBLE)
                closeCircleReveal()
        }
    }

    private fun startCircleReveal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pivotX = binding.secondLayout.width / 2
            val pivotY = binding.secondLayout.height / 2

            val finalRadius = hypot(pivotX.toDouble(), pivotY.toDouble()).toFloat()

            val anim = ViewAnimationUtils.createCircularReveal(
                binding.secondLayout,
                pivotX,
                pivotY,
                0f,
                finalRadius
            )
            anim.duration = 500
            binding.secondLayout.visibility = View.VISIBLE
            anim.start()
        } else
            binding.secondLayout.visibility = View.INVISIBLE
    }

    private fun closeCircleReveal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val pivotX = binding.secondLayout.width / 2
            val pivotY = binding.secondLayout.height / 2

            val finalRadius = hypot(pivotX.toDouble(), pivotY.toDouble()).toFloat()

            val anim = ViewAnimationUtils.createCircularReveal(
                binding.secondLayout,
                pivotX,
                pivotY,
                finalRadius,
                0f
            )
            anim.duration = 500
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    binding.secondLayout.visibility = View.INVISIBLE
                }
            })
            anim.start()
        } else
            binding.secondLayout.visibility = View.VISIBLE
    }
}