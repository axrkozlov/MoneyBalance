package com.axfex.moneybalance.utils


import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager




class SelectionRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {


    lateinit var itemDecoration:SelectionDecoration

    fun <K : Any, T : ViewHolder> initialize(
        newAdapter: ListAdapter<K, T>,
        position: Int? = null,
        selectionView: Drawable
    ) {

        layoutManager = GridLayoutManager(context,4)
        itemDecoration=SelectionDecoration(selectionView)
        addItemDecoration(itemDecoration)
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(this)
        adapter = newAdapter

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                position?.let { scrollToPosition(it) }
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }

        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }


    fun select(position:Int){
        itemDecoration.select(position)
    }

    class SelectionDecoration(val selectionView:Drawable) : ItemDecoration() {
        var selectedPosition:Int? = null

        override fun onDrawOver(c: Canvas, parent: RecyclerView) {


        }

        override fun onDraw(c: Canvas, parent: RecyclerView) {

            // this will iterate over every visible view
            for (i in 0 until parent.childCount) {
                // get the view
                val view = parent.getChildAt(i)

                val params = view.layoutParams as RecyclerView.LayoutParams
                val height = view.layoutParams.height
                val width = view.layoutParams.width
                // get the position
                val position = params.viewAdapterPosition
                view.setOnClickListener {
                    Log.i("SelectionDecoration", "onDrawOver: ")
                    selectedPosition=position
                }
                selectionView.setBounds(0,0,500,500)

                // and finally draw the separator
                if (position == selectedPosition) {
                    selectionView.draw(c)
                }
            }
        }

        fun select(position:Int){
            selectedPosition=position

        }
    }


}