package com.axfex.moneybalance.ui.widgets


import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import kotlin.math.max


class IconRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {


//    private var selectionCallback: ((selectedIconName: Int) -> Unit)? = null

    private lateinit var itemDecoration: SelectionDecoration

//    var selectedIconName: Int = 0

    init {
        layoutManager = GridLayoutManager(context, 4)
    }

    fun initialize(
        selectionView: Drawable
    ) {

        itemDecoration = SelectionDecoration(selectionView)
        addItemDecoration(itemDecoration)

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }

        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }

    inner class SelectionDecoration(private val selectionView: Drawable) : ItemDecoration() {


        override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {

            for (i in 0 until parent.childCount) {

//                val view = parent.getChildAt(i)
//                val params = view.layoutParams as LayoutParams
//                val position = params.viewAdapterPosition
//                view.setOnClickListener { select(position) }

//                if (selectedIconName == position) {
//                    val dimen = max(view.right - view.left, view.bottom - view.top)
//
//                    selectionView.setBounds(view.left, view.top, view.left + dimen, view.top + dimen)
//                    selectionView.draw(c)
//                }

                val view = parent.getChildAt(i)
                if (view.isActivated) {

                    val dimen = max(view.right - view.left, view.bottom - view.top)
                    selectionView.setBounds(view.left, view.top, view.left + dimen, view.top + dimen)
                    selectionView.draw(c)

                }

            }

            super.onDraw(c, parent, state)
        }

    }

//    fun select(position: Int) {
//        adapter?.notifyItemChanged(selectedIconName, "payload $selectedIconName")
//        selectedIconName = position
//        adapter?.notifyItemChanged(selectedIconName, "payload $selectedIconName")
//
//        if (
//            (layoutManager as GridLayoutManager).findFirstCompletelyVisibleItemPosition()>position ||
//            (layoutManager as GridLayoutManager).findLastCompletelyVisibleItemPosition()<position
//        ) scrollToPosition(position)
//        selectionCallback?.let { it(selectedIconName) }
//    }

//    fun onSaveInstanceState(state: Bundle?) {
//        state?.putInt("selectedIconName", selectedIconName)
//    }
//
//    fun onRestoreInstanceState(state: Bundle?) {
//        state?.let {
//            selectedIconName = it.getInt("selectedIconName", 0)
//        }
//    }
//
//    fun getCurrentPosition(): Int {
//        return selectedIconName
//    }
//
//    fun setSelectionChangeCallback(callback: (selectedIconName: Int) -> Unit) {
//        selectionCallback = callback
//        selectionCallback?.let { it(selectedIconName) }
//
//    }


}