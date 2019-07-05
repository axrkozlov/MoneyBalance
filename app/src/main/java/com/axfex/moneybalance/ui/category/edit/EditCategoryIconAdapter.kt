package com.axfex.moneybalance.ui.category.edit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.icon.Icon

class EditCategoryIconAdapter: ListAdapter<Icon,EditCategoryIconAdapter.ViewHolder>(DrawableDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_edit_category_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(val view : View): RecyclerView.ViewHolder(view){
        fun bind(icon: Icon){
            val image= view.findViewById<ImageView>(R.id.categoryIcon)
            val name= view.findViewById<TextView>(R.id.categoryIconName)

            image.setImageDrawable(icon.image)
            name.text=icon.name.split(".")[0]

        }
    }

    class DrawableDiffCallback:DiffUtil.ItemCallback<Icon>(){
        override fun areContentsTheSame(oldItem: Icon, newItem: Icon): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: Icon, newItem: Icon): Boolean {
            return oldItem.equals(newItem)
        }

    }

}
