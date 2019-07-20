package com.axfex.moneybalance.ui.category.edit

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.azoft.carousellayoutmanager.CarouselLayoutManager

class EditCategoryColorAdapter: ListAdapter<Int,EditCategoryColorAdapter.ViewHolder>(IconColorDiffCallback()) {


    private lateinit var recyclerView: RecyclerView
    private var pendingSelectionColor: Int = -1
    private var colorList: List<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_edit_category_color_item,parent,false)
        return ViewHolder(view)
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
        super.onAttachedToRecyclerView(recyclerView)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun select(position:Int):Boolean{
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
        val position =(recyclerView.layoutManager as CarouselLayoutManager).centerItemPosition
        return getItem(position)
    }

    override fun submitList(list: List<Int>?) {
        super.submitList(list)
        colorList = list
        if (pendingSelectionColor==-1) select(pendingSelectionColor)

    }

    inner class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
        fun bind(iconColor: Int){

            val drawable= view.findViewById<ImageView>(R.id.categoryColor).drawable.mutate() as GradientDrawable
            drawable.setColor(iconColor)
        }
    }

    class IconColorDiffCallback:DiffUtil.ItemCallback<Int>(){
        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

    }

}
