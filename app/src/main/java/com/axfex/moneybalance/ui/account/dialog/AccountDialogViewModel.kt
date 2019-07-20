package com.axfex.moneybalance.ui.account.dialog

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository

class AccountDialogViewModel(val repo: Repository) : ViewModel() {
    fun accountList()=repo.accountList()

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

}
