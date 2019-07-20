package com.axfex.moneybalance.ui.widgets


import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import android.graphics.drawable.Drawable
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow


class ColorRecyclerView(
    context: Context,
    attrs: AttributeSet
) : RecyclerView(context, attrs) {

    private var startArrow: Drawable? = null
    private var endArrow: Drawable? = null

    init {
        layoutManager = CarouselLayoutManager(HORIZONTAL)
    }

    fun initialize(
        startArrow: Drawable? = null,
        endArrow: Drawable? = null
    ) {


        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(this)

        this.startArrow = startArrow
        this.endArrow = endArrow


        addOnScrollListener(object : OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                onScrollChanged()
            }
        })

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                setupArrows()
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }

        viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }


    private fun onScrollChanged() {

        post {

            (0 until childCount).forEach { position ->
                val child = getChildAt(position)
                val childCenterX = (child.left + child.right) / 2
                val scaleValue = getGaussianScale(childCenterX, 1f, 1f, 150.0)
                child.scaleX = scaleValue
                child.scaleY = scaleValue
            }
        }
        setupArrows()
    }

    private fun getGaussianScale(
        childCenterX: Int,
        minScaleOffset: Float,
        scaleFactor: Float,
        spreadFactor: Double
    ): Float {
        val recyclerCenterX = (left + right) / 2
        return (Math.E.pow(
            -(childCenterX - recyclerCenterX.toDouble()).pow(2.0) / (2 * spreadFactor.pow(2.0))
        ) * scaleFactor + minScaleOffset).toFloat()
    }

    private fun setupArrows() {
        val firstView = layoutManager?.findViewByPosition(0)
        val endView = layoutManager?.findViewByPosition(adapter!!.itemCount - 1)
        startArrow?.let {
            it.setBounds(0, (height * 0.2).toInt(), (height * 0.8).toInt(), (height * 0.8).toInt())
            if (firstView == null) {
                it.alpha = 0

            } else {
                val offset = firstView.left
                val offsetPercent = max(0.0, offset.toDouble() / (width / 2))
                it.alpha = min(255, (255 * offsetPercent).toInt())
            }
        }
        endArrow?.let {
            it.setBounds((width - 0.8 * height).toInt(), (height * 0.2).toInt(), width, (height * 0.8).toInt())
            if (endView == null) {
                it.alpha = 0
            } else {
                val offset = width - endView.right
                val offsetPercent = max(0.0, offset.toDouble() / (width / 2))
                it.alpha = min(255, (255 * offsetPercent).toInt())

            }
        }

        
    }

    override fun onDraw(c: Canvas?) {

        super.onDraw(c)
        c?.let {
            startArrow?.draw(c)
            endArrow?.draw(c)
        }
    }


}