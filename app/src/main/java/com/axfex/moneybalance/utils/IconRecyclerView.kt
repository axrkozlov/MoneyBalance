package com.axfex.moneybalance.utils


import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_edit_category.*
import kotlin.math.max


class IconRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {


    private var selectionCallback: ((selectedPosition: Int) -> Unit)? = null

    private lateinit var itemDecoration: SelectionDecoration

    var selectedPosition: Int = 0

    init{
        layoutManager = GridLayoutManager(context, 4)
    }

    fun initialize(
        position: Int? = null,
        selectionView: Drawable
    ) {

        itemDecoration = SelectionDecoration(selectionView)
        addItemDecoration(itemDecoration)

        position?.let{selectedPosition=it}

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                scrollToPosition(selectedPosition)
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }

        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    inner class SelectionDecoration(private val selectionView: Drawable) : ItemDecoration() {


        override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {

            for (i in 0 until parent.childCount) {

                val view = parent.getChildAt(i)
                val params = view.layoutParams as LayoutParams
                val position = params.viewAdapterPosition
                view.setOnClickListener { select(position) }

                if (selectedPosition == position) {
                    val dimen = max(view.right - view.left, view.bottom - view.top)

                    selectionView.setBounds(view.left, view.top, view.left + dimen, view.top + dimen)
                    selectionView.draw(c)
                }


            }

            super.onDraw(c, parent, state)
        }

    }

    fun select(position: Int) {
        adapter?.notifyItemChanged(selectedPosition, "payload $selectedPosition")
        selectedPosition = position
        adapter?.notifyItemChanged(selectedPosition, "payload $selectedPosition")

        if (
            (layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()>position ||
            (layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()<position
        ) scrollToPosition(position)
        selectionCallback?.let { it(selectedPosition) }
    }

    fun onSaveInstanceState(state: Bundle?) {
        state?.putInt("selectedPosition", selectedPosition)
    }

    fun onRestoreInstanceState(state: Bundle?) {
        state?.let {
            selectedPosition = it.getInt("selectedPosition", 0)
        }
    }

    fun getCurrentPosition(): Int {
        return selectedPosition
    }

    fun setSelectionChangeCallback(callback: (selectedPosition: Int) -> Unit) {
        selectionCallback = callback
        selectionCallback?.let { it(selectedPosition) }

    }


}