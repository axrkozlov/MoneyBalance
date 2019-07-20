package com.axfex.moneybalance.ui.category.list

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.category.CategoryListView
import kotlinx.android.synthetic.main.fragment_category_list_item.view.*


class CategoryListAdapter(val viewModel: CategoryListViewModel) :
    ListAdapter<CategoryListView, CategoryListAdapter.ViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_category_list_item, parent, false)
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
                val direction =
                    CategoryListFragmentDirections.actionCategoryListFragmentToEditCategoryFragment(category.id,category.type)

                itemView.findNavController().navigate(direction)
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
