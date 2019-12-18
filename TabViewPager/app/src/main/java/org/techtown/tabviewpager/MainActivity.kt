package org.techtown.tabviewpager

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tab_button.view.*
import org.techtown.tabviewpager.Adapter.PageAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var mContext : Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mContext = applicationContext
        initViewPager() // 뷰페이저와 어댑터 장착
    }

    private fun createView(tabName: String): View {
        var tabView = LayoutInflater.from(mContext).inflate(R.layout.custom_tab_button, null)

        tabView.tab_text.text = tabName
        when (tabName) {
            "찾기" -> {
                tabView.tab_logo.setImageResource(android.R.drawable.ic_menu_search)
                return tabView
            }
            "사진" -> {
                tabView.tab_logo.setImageResource(android.R.drawable.ic_menu_camera)
                return tabView
            }
            "전화" -> {
                tabView.tab_logo.setImageResource(android.R.drawable.ic_menu_call)
                return tabView
            }
            else -> {
                return tabView
            }
        }
    }

    private fun initViewPager(){
        val searchFragment = FragmentTab()
        searchFragment.name = "찾기 창"
        val cameraFragment = FragmentTab()
        cameraFragment.name = "사진 창"
        val callFragment = FragmentTab()
        callFragment.name = "전화 창"


        val adapter = PageAdapter(supportFragmentManager) // PageAdapter 생성
        adapter.addItems(searchFragment)
        adapter.addItems(cameraFragment)
        adapter.addItems(callFragment)


        main_viewPager.adapter = adapter // 뷰페이저에 adapter 장착
        main_tablayout.setupWithViewPager(main_viewPager) // 탭레이아웃과 뷰페이저를 연동


        main_tablayout.getTabAt(0)?.setCustomView(createView("찾기"))
        main_tablayout.getTabAt(1)?.setCustomView(createView("사진"))
        main_tablayout.getTabAt(2)?.setCustomView(createView("전화"))

//        main_tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
//            override fun onTabReselected(p0: TabLayout.Tab?) {}
//
//            override fun onTabUnselected(p0: TabLayout.Tab?) {}
//
//            override fun onTabSelected(p0: TabLayout.Tab?) {}
//        })

    }
}
