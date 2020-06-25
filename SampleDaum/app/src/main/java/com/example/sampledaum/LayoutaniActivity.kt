package com.example.sampledaum


import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair as UtilPair
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.sampledaum.rcv.ItemDeco
import com.example.sampledaum.rcv.ProductAdapter
import com.example.sampledaum.rcv.ProductData
import kotlinx.android.synthetic.main.activity_layoutani.*
import kotlinx.android.synthetic.main.activity_second_layout.view.*


class LayoutaniActivity : AppCompatActivity() {

    private lateinit var adapter : ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layoutani)

        initRcv()


    }

    private fun initRcv(){
        adapter = ProductAdapter(this)
        layout_rcv.adapter = adapter
        layout_rcv.apply {
            layoutManager = GridLayoutManager(this@LayoutaniActivity,
            2)
            addItemDecoration(ItemDeco(this@LayoutaniActivity,true,10))
        }
        adapter.apply {
            onAddItem(
                ProductData("https://cdn.pixabay.com/photo/2020/05/12/17/04/wind-turbine-5163993__340.jpg",
                    "Windmill","Beautiful Windmill"))
            onAddItem(
                ProductData("https://cdn.pixabay.com/photo/2020/06/15/17/35/me-nots-5302712__340.jpg",
                    "Flower","Wow Flower"))
            onAddItem(
                ProductData("https://cdn.pixabay.com/photo/2020/06/09/18/11/box-write-in-it-5279529__340.jpg",
                    "Frame","Flower Frame"))
            onAddItem(
                ProductData("https://cdn.pixabay.com/photo/2020/06/02/11/12/eiffel-tower-5250503__340.png",
                    "Eiffel Tower","Eiffel Tower of various colors"))
            onAddItem(
                ProductData("https://cdn.pixabay.com/photo/2017/02/12/22/21/pocket-watch-2061228__340.jpg",
                    "Clock","an antique watch"))
            onAddItem(
                ProductData("https://cdn.pixabay.com/photo/2016/10/22/16/51/basketball-1761149__340.jpg",
                    "BasketBall","Let's Play"))
        }
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickListener(object : ProductAdapter.OnItemClickListener{
            override fun onItemClick(v: View, data : ProductData) {


                val intent = Intent(this@LayoutaniActivity, SecondLayoutActivity::class.java)
                intent.putExtra("data",data)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//                    val pair1 = UtilPair.create<View,String>(v.product_img,"imageTransition")
//                    val pair2 = UtilPair.create<View,String>(v.product_txt,"titleTransition")

                    val options : ActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                        this@LayoutaniActivity,v.product_img,"imageTransition"
                    )
                    startActivity(intent,options.toBundle())
                }
                else{
                    startActivity(intent)
                }


            }
        })


    }
}