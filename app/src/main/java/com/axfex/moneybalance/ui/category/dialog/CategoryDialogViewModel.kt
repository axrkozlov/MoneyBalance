package com.axfex.moneybalance.ui.category.dialog

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository

class CategoryDialogViewModel(val repo: Repository) : ViewModel() {
    fun categoryList()=repo.categoryList()

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

}
