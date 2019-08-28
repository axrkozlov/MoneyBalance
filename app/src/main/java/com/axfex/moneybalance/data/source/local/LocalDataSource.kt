package com.axfex.moneybalance.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.axfex.moneybalance.data.source.local.dao.AccountsDao
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.data.source.local.dao.OperationsDao
import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.icon.Icon
import com.axfex.moneybalance.domain.model.operation.Operation
import com.axfex.moneybalance.domain.model.operation.OperationListView


class LocalDataSource(
    val iconsDao: IconsDao,
    val categoriesDao: CategoriesDao,
    val accountsDao: AccountsDao,
    val operationsDao: OperationsDao
)  {

    fun iconList()=iconsDao.iconList()
    fun icon(name:String) = iconsDao.icon(name)
    fun insertIcons(vararg icons: Icon) = iconsDao.insertIcons(*icons)

    fun categoryList()=categoriesDao.categoryList()
    fun category(categoryId: String) = categoriesDao.category(categoryId)
    fun insertCategory(vararg category: Category) = categoriesDao.insertCategory(*category)
    fun deleteCategory(categoryId: String) =  categoriesDao.deleteCategory(categoryId)
    fun getOneCategory() = categoriesDao.getOneCategory()

    fun accountList()=accountsDao.accountList()
    fun account(accountId: String) = accountsDao.account(accountId)
    fun insertAccount(vararg account: Account) = accountsDao.insertAccount(*account)
    fun deleteAccount(accountId: String) =  accountsDao.deleteAccount(accountId)
    fun getOneAccount() = accountsDao.getOneAccount()


    fun operationList()=operationsDao.operationList()
    fun insertOperation(vararg operation: Operation)=operationsDao.insertOperation(*operation)
//    val operationPagedList: LiveData<PagedList<OperationListView>> = operationsDao.operationPagedList().
//        .toLiveData(pageSize = 50)
}