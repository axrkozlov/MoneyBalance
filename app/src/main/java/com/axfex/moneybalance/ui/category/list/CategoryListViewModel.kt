package com.axfex.moneybalance.ui.category.list

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.domain.model.icon.Icon

class CategoryListViewModel(val repo:Repository) : ViewModel() {

    fun expenseCategoryList()=repo.expenseCategoryList()
    fun incomeCategoryList()=repo.incomeCategoryList()

    fun getIconDrawable(iconName: String) = repo.getIconDrawable(iconName)

    fun categoryList()=repo.categoryList()
}
