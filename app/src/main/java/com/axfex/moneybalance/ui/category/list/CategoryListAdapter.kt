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
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.category.IncomeCategory
import com.axfex.moneybalance.ui.category.CategoryTypesEnum
import kotlinx.android.synthetic.main.fragment_category_list_expense_item.view.*


class CategoryListAdapter(val viewModel: CategoryListViewModel) :
    ListAdapter<Category, CategoryListAdapter.ViewHolder>(CategoryDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_category_list_expense_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(category: Category) {

            itemView.categoryName.text = category.name
            itemView.categoryIcon.setImageDrawable(viewModel.getIconDrawable(category.icon))
            val drawable = itemView.categoryColor.drawable.mutate() as GradientDrawable
            drawable.setColor(category.icon.backgroundColor)

            itemView.setOnClickListener {

                val type=when(category){
                    is ExpenseCategory -> CategoryTypesEnum.EXPENSE_CATEGORY
                    is IncomeCategory -> CategoryTypesEnum.INCOME_CATEGORY
                    else -> throw Exception("unknown class found")
                }
                val direction =
                    CategoryListFragmentDirections.actionCategoryListFragmentToEditCategoryFragment(category.id,type)

                itemView.findNavController().navigate(direction)
            }
        }

    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

}
