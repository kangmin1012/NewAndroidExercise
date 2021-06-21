package com.example.samplespringanim

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.lifecycle.lifecycleScope
import com.example.samplespringanim.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var xDown = 0f
    var yDown = 0f

    private lateinit var animX: SpringAnimation
    private lateinit var animY: SpringAnimation
    private lateinit var anim2x: SpringAnimation
    private lateinit var anim2y: SpringAnimation

    private lateinit var firstLayoutParams: ViewGroup.MarginLayoutParams
    private lateinit var secondLayoutParams: ViewGroup.MarginLayoutParams

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        firstLayoutParams = binding.imgBallSky500.layoutParams as ViewGroup.MarginLayoutParams
        secondLayoutParams = binding.imgBallSky700.layoutParams as ViewGroup.MarginLayoutParams

        setSpringAnimation()

        animX.addUpdateListener { _, value, _ ->
            anim2x.animateToFinalPosition(value)
        }

        animY.addUpdateListener { _, value, _ ->
            anim2y.animateToFinalPosition(value + secondLayoutParams.topMargin + binding.imgBallSky700.height)
        }

        binding.imgBallSky200.setOnTouchListener { v, event ->

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    xDown = binding.imgBallSky200.x - event.rawX
                    yDown = binding.imgBallSky200.y - event.rawY
                }

                MotionEvent.ACTION_MOVE -> {
                    val newX = event.rawX + xDown
                    val newY = event.rawY + yDown

                    binding.imgBallSky200.animate()
                        .x(newX)
                        .y(newY)
                        .setDuration(0)
                        .start()

                    animX.animateToFinalPosition(newX)
                    animY.animateToFinalPosition(newY + firstLayoutParams.topMargin + binding.imgBallSky500.height)
                }

                MotionEvent.ACTION_UP -> {

                }
            }

            true
        }


    }

    private fun setSpringAnimation() {
        animX = SpringAnimation(binding.imgBallSky500, DynamicAnimation.X,0f)
        animY = SpringAnimation(binding.imgBallSky500, DynamicAnimation.Y,0f)
        anim2x = SpringAnimation(binding.imgBallSky700, DynamicAnimation.X,0f)
        anim2y = SpringAnimation(binding.imgBallSky700, DynamicAnimation.Y,0f)

        setStiffness(SpringForce.STIFFNESS_LOW)
        setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
    }

    private fun setStiffness(stiffness: Float) {
        animX.spring.stiffness = stiffness
        anim2x.spring.stiffness = stiffness
        animY.spring.stiffness = stiffness
        anim2y.spring.stiffness = stiffness
    }

    private fun setDampingRatio(dampingRatio: Float) {
        animX.spring.dampingRatio = dampingRatio
        anim2x.spring.dampingRatio = dampingRatio
        animY.spring.dampingRatio = dampingRatio
        anim2y.spring.dampingRatio = dampingRatio
    }

}