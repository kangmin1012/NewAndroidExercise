package org.sample.motionlayoutsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addItems(SampleFragment())
        adapter.addItems(SampleFragment())
        adapter.addItems(SampleFragment())



        sample_viewpager.adapter = adapter
        sample_viewpager.offscreenPageLimit = 2
        sample_viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                val progress = (position + positionOffset) / (3 - 1)
                motion_layout.progress = progress
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageSelected(position: Int) {
            }
        })


        sample_tab.setupWithViewPager(sample_viewpager)

        sample_tab.apply {
            getTabAt(0)?.text = "tab1"
            getTabAt(1)?.text = "tab2"
            getTabAt(2)?.text = "tab2"
        }



    }
}