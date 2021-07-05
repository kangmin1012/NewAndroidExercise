package org.techtown.samplepagesliding


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var isPageOpen : Boolean = false
    lateinit var LeftAnim : Animation
    lateinit var RightAnim : Animation


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        LeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left)
        RightAnim = AnimationUtils.loadAnimation(this,R.anim.translate_right)

        LeftAnim.setAnimationListener(SlidingPageAnimationListener())
        RightAnim.setAnimationListener(SlidingPageAnimationListener())

        button.setOnClickListener {
            if (isPageOpen) {
                page.startAnimation(RightAnim)
            }
            else{
                page.visibility = View.VISIBLE
                page.startAnimation(LeftAnim)
            }
        }
    }

    private inner class SlidingPageAnimationListener : Animation.AnimationListener {

        override fun onAnimationEnd(animation: Animation?) : Unit {
            if(isPageOpen){
                page.visibility=View.INVISIBLE

                button.text="Open"
                isPageOpen = false
            }
            else{
                button.text="Close"
                isPageOpen = true
            }
        }

        override fun onAnimationStart(animation: Animation?) {

        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    }
}