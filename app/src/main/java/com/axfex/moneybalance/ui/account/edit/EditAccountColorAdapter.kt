package com.axfex.moneybalance.ui.account.edit

import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.VectorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import kotlinx.android.synthetic.main.fragment_edit_account_color_item.view.*

class EditAccountColorAdapter : ListAdapter<Int, EditAccountColorAdapter.ViewHolder>(IconColorDiffCallback()) {

    private lateinit var recyclerView: RecyclerView
    private var pendingSelectionColor: Int = -1
    private var colorList: List<Int>? = null
    var currentIcon: Drawable? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_edit_account_color_item, parent, false)
        return ViewHolder(view)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun select(position: Int): Boolean {
        if (position < 0 || position >= itemCount) return false
        (recyclerView.layoutManager as CarouselLayoutManager).scrollToPosition(position)
        return true
    }

    fun selectByColor(color: Int) {
        colorList?.let {
            for (i in 0 until it.size) {
                if (it[i] == color) {
                    if (select(i)) {
                        pendingSelectionColor = -1
                        return@selectByColor
                    }
                }
            }
        }
        pendingSelectionColor = color
    }

    fun selectedColor(): Int {
        val position = (recyclerView.layoutManager as CarouselLayoutManager).centerItemPosition
        return getItem(position)
    }

    override fun submitList(list: List<Int>?) {
        super.submitList(list)
        colorList = list
        if (pendingSelectionColor == -1) select(pendingSelectionColor)

    }

    fun setIcon(iconDrawable: Drawable) {
        currentIcon = iconDrawable
        for (i in 0 until itemCount) {
            val view = (recyclerView.layoutManager as CarouselLayoutManager).findViewByPosition(i)?.accountColor
            val drawable = iconDrawable.constantState?.newDrawable()?.mutate()
            colorList?.get(i)?.let {
                drawable?.setTint(it)
            }
            view?.setImageDrawable(drawable)

        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(iconColor: Int) {

//            var drawable: Drawable?
            var drawable = if (currentIcon != null) {
                currentIcon!!.constantState?.newDrawable()?.mutate()
            } else {
                view.accountColor.drawable.constantState?.newDrawable()?.mutate()
            }
            view.accountColor.setImageDrawable(drawable)
            drawable?.setTint(iconColor)
        }
    }

    class IconColorDiffCallback : DiffUtil.ItemCallback<Int>() {
        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem.equals(newItem)
        }

    }

}
