package com.axfex.moneybalance.ui.category.dialog

import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.category.CategoryListView
import com.axfex.moneybalance.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_category_dialog_item.view.*

class CategoryDialogAdapter(val viewModel: CategoryDialogViewModel, val mainViewModel:MainViewModel) :
    ListAdapter<CategoryListView, CategoryDialogAdapter.ViewHolder>(CategoryDiffCallback()) {

    var dismissCallback:(() -> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_category_dialog_item,parent,false)
        return ViewHolder(view)
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: CategoryListView) {

            itemView.categoryName.text = category.name
            itemView.categoryIcon.setImageDrawable(viewModel.getIconDrawable(category.iconName))
            val drawable = itemView.categoryColor.drawable.mutate() as GradientDrawable
            drawable.setColor(category.color)

            itemView.setOnClickListener {
                mainViewModel.pickExpenseCategory(category.id,category.type)

                it.postDelayed({dismissCallback?.invoke()},100)
            }
        }
        

    }


    class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryListView>() {
        override fun areContentsTheSame(oldItem: CategoryListView, newItem: CategoryListView): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: CategoryListView, newItem: CategoryListView): Boolean {
            return oldItem == newItem
        }

    }
}
