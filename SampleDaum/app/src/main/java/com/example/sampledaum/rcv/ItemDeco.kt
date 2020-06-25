package com.example.sampledaum.rcv

import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemDeco (context : Context, isGrid : Boolean = false, size : Int = 20) : RecyclerView.ItemDecoration(){
    private val sizeSpace : Int
    private val checking = isGrid

    init{
        sizeSpace = dpToPx(context,size)
    }

    private fun dpToPx(context: Context, dp : Int): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when(checking){
            false -> {
                if(parent.getChildAdapterPosition(view) != state.itemCount - 1){
                    outRect.bottom = sizeSpace
                }
            }

            true ->{
                val lp = view.layoutParams as GridLayoutManager.LayoutParams
                // spanIndex -> 그리드 뷰 하나의 행의 순서
                when (lp.spanIndex) {
                    0 -> {
                        outRect.left = sizeSpace / 3

                        outRect.right = sizeSpace / 2
                    }
                    lp.spanSize -> {
                        outRect.right = sizeSpace / 3
                        outRect.left = sizeSpace / 2
                    }
                    else -> {
                        outRect.left = sizeSpace / 2
                        outRect.right = sizeSpace / 2
                    }
                }

                outRect.bottom = sizeSpace
            }
        }



    }
}