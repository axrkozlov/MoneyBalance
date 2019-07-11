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

class EditCategoryColorAdapter: ListAdapter<Int,EditCategoryColorAdapter.ViewHolder>(IconColorDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_edit_category_color_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
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
            return oldItem.equals(newItem)
        }

    }

}
