package com.axfex.moneybalance.ui.account.list

import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.account.AccountListView
import kotlinx.android.synthetic.main.fragment_account_list_item.view.*


class AccountListAdapter(val viewModel: AccountListViewModel) :
    ListAdapter<AccountListView, AccountListAdapter.ViewHolder>(AccountDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_account_list_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(accountList: AccountListView) {

            itemView.accountName.text = accountList.name

            itemView.accountIcon.setImageDrawable(viewModel.getIconDrawable(accountList.iconName))
            val drawable = view.accountIcon.drawable
            drawable.setTint(accountList.color)



            itemView.setOnClickListener {
                val direction =
                    AccountListFragmentDirections.actionAccountListFragmentToEditAccountFragment(accountList.id)

                itemView.findNavController().navigate(direction)
            }


        }

    }

    class AccountDiffCallback : DiffUtil.ItemCallback<AccountListView>() {
        override fun areContentsTheSame(oldItem: AccountListView, newItem: AccountListView): Boolean {
            return newItem == oldItem
        }

        override fun areItemsTheSame(oldItem: AccountListView, newItem: AccountListView): Boolean {
            return oldItem == newItem
        }

    }

}
