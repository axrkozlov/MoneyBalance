package com.axfex.moneybalance.data.source

import android.util.Log
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.remote.RemoteDataSource
import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.icon.IconsManager
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.icon.Icon
import com.axfex.moneybalance.domain.model.operation.Operation

class Repository(val lds: LocalDataSource, val rds: RemoteDataSource, val iconsManager: IconsManager) {

    fun iconList() = lds.iconList()
    fun icon(name: String) = lds.icon(name)
    fun colorList() = iconsManager.colorList()
    fun getIconDrawable(iconName: String) = iconsManager.getIconDrawable(iconName)
//    fun insertIcons(vararg icons: Icon) = lds.insertIcons(*icons)

    fun categoryList() = lds.categoryList()
    fun category(categoryId: String) = lds.category(categoryId)
    fun insertCategory(vararg category: Category) = lds.insertCategory(*category)
    fun deleteCategory(categoryId: String) = lds.deleteCategory(categoryId)

    fun accountList() = lds.accountList()
    fun account(accountId: String) = lds.account(accountId)
    fun insertAccount(vararg account: Account) = lds.insertAccount(*account)
    fun deleteAccount(accountId: String) = lds.deleteAccount(accountId)

    fun operationList() = lds.operationList()
    fun insertOperation(operation: Operation) = lds.insertOperation(operation)

}

