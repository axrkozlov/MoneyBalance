package com.axfex.moneybalance.ui.account.dialog

import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.axfex.moneybalance.R
import com.axfex.moneybalance.domain.model.account.AccountListView
import com.axfex.moneybalance.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_account_dialog_item.view.*
import kotlinx.android.synthetic.main.fragment_account_dialog_item.view.accountIcon
import kotlinx.android.synthetic.main.fragment_account_dialog_item.view.accountName
import kotlinx.android.synthetic.main.fragment_account_list_item.view.*

class AccountDialogAdapter(val viewModel: AccountDialogViewModel, val mainViewModel:MainViewModel) :
    ListAdapter<AccountListView, AccountDialogAdapter.ViewHolder>(AccountDiffCallback()) {

    var dismissCallback:(() -> Unit)?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.fragment_account_dialog_item,parent,false)
        return ViewHolder(view)
        
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(account: AccountListView) {

            itemView.accountName.text = account.name
            itemView.accountIcon.setImageDrawable(viewModel.getIconDrawable(account.iconName))
            val drawable = view.accountIcon.drawable
            drawable.setTint(account.color)

            itemView.setOnClickListener {
                mainViewModel.pickAccount(account.id)

                it.postDelayed({dismissCallback?.invoke()},100)
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
