package com.axfex.moneybalance.ui.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.account.Account
import com.axfex.moneybalance.domain.category.Category


class AccountListAdapter(val viewModel: AccountListViewModel) :
    ListAdapter<Account, AccountListAdapter.ViewHolder>(AccountDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_category_list_expense_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(account: Account) {

//            itemView.categoryName.text = incomeCategory.name
//            itemView.categoryIcon.setImageDrawable(viewModel.getIconDrawable(incomeCategory.icon))
//            val drawable = itemView.categoryColor.drawable.mutate() as GradientDrawable
//            drawable.setColor(incomeCategory.icon.backgroundColor)
//
//            itemView.setOnClickListener {
//
//                val direction =
//                    CategoryListFragmentDirections.actionCategoryListFragmentToEditCategoryFragment(incomeCategory.id)
//                itemView.findNavController().navigate(direction)
//            }
        }

    }

    class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
        override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
            return oldItem == newItem
        }

    }

}
