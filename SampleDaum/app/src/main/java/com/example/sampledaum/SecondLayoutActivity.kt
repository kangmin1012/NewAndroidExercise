package com.example.sampledaum

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.bumptech.glide.Glide
import com.example.sampledaum.rcv.ProductData
import kotlinx.android.synthetic.main.activity_second_layout.*

class SecondLayoutActivity : AppCompatActivity() {
    private lateinit var mDetail : ProductData

    override fun onEnterAnimationComplete() {
        super.onEnterAnimationComplete()
//        setUi()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_layout)

        val intent = intent
        mDetail = intent.getSerializableExtra("data") as ProductData


    }

    override fun onStart() {
        super.onStart()
        setUi()
    }


    private fun setUi(){
        Glide.with(this).load(mDetail.productImg).into(product_img)
        product_txt.text = mDetail.productTitle
        detail_content_txt.text = mDetail.productContent
    }

}