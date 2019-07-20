package com.axfex.moneybalance.ui.account.list

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository

class AccountListViewModel(val repo:Repository) : ViewModel() {

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    fun accountList()=repo.accountList()
}
