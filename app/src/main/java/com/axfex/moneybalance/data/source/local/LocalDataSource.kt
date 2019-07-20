package com.axfex.moneybalance.data.source.local

import com.axfex.moneybalance.data.source.OperationsDataSource
import com.axfex.moneybalance.data.source.local.dao.AccountsDao
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.data.source.local.dao.OperationsDao
import com.axfex.moneybalance.domain.model.account.CreditAccount
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.icon.Icon
import com.axfex.moneybalance.domain.model.operation.Operation
import java.math.BigDecimal


class LocalDataSource(
    val iconsDao: IconsDao,
    val categoriesDao: CategoriesDao,
    val accountsDao: AccountsDao,
    val operatiosDao: OperationsDao
)  {



    fun getAmounts(): List<BigDecimal>? {
        return null
    }


    fun insertIcons(vararg icons: Icon) = iconsDao.insertIcons(*icons)
    fun icon(name:String) = iconsDao.icon(name)
    fun iconList()=iconsDao.iconList()


    fun categoryList()=categoriesDao.categoryList()


    fun expenseCategoryList() = categoriesDao.expenseCategoryList()
    fun incomeCategoryList() = categoriesDao.incomeCategoryList()


    fun expenseCategory(categoryId: String) = categoriesDao.expenseCategory(categoryId)
    fun incomeCategory(categoryId: String) = categoriesDao.incomeCategory(categoryId)

    fun insertExpenseCategory(vararg category: ExpenseCategory) = categoriesDao.insertExpenseCategory(*category)
    fun insertIncomeCategory(vararg category: IncomeCategory) = categoriesDao.insertIncomeCategory(*category)

    fun deleteIncomeCategory(categoryId: String) =  categoriesDao.deleteIncomeCategory(categoryId)
    fun deleteExpenseCategory(categoryId: String) =  categoriesDao.deleteExpenseCategory(categoryId)

    fun accountList()=accountsDao.accountList()

    fun creditAccountList() = accountsDao.creditAccountList()

    fun creditAccount(accountId: String) = accountsDao.creditAccount(accountId)

    fun insertCreditAccount(vararg account: CreditAccount) = accountsDao.insertCreditAccount(*account)

    fun deleteCreditAccount(accountId: String) =  accountsDao.deleteCreditAccount(accountId)

}