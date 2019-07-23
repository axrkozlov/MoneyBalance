package com.axfex.moneybalance.ui.operation.list

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository

class OperationListViewModel(val repo:Repository) : ViewModel() {

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    fun operationList()=repo.operationList()


}
