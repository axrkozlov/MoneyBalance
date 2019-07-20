package com.axfex.moneybalance.data.source

import android.util.Log
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.remote.RemoteDataSource
import com.axfex.moneybalance.domain.model.account.CreditAccount
import com.axfex.moneybalance.domain.model.icon.IconsManager
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.icon.Icon

class Repository(val lds: LocalDataSource, val rds: RemoteDataSource, val iconsManager: IconsManager) {
    init {
        Log.i("Repository", "Repository: ")
        getAmounts()

    }


    fun getAmounts() {
        lds.getAmounts()
    }

    fun colorList() = iconsManager.colorList()

    fun getIconDrawable(iconName: String) = iconsManager.getIconDrawable(iconName)




//    fun expenseCategoryList(): LiveData<List<Category>> = map(
//        lds.expenseCategoryList()
//    ) { expenseCategoryList ->
//        expenseCategoryList.forEach { incomeCategory ->
//            setIconDrawable(incomeCategory.icon)
//        }
//        return@map expenseCategoryList
//
//    }

    fun iconList()=lds.iconList()

    fun categoryList()=lds.categoryList()

    fun expenseCategoryList() = lds.expenseCategoryList()
    fun incomeCategoryList() = lds.incomeCategoryList()


    fun expenseCategory(categoryId: String) = lds.expenseCategory(categoryId)
    fun incomeCategory(categoryId: String) = lds.incomeCategory(categoryId)

    fun insertExpenseCategory(category: ExpenseCategory) = lds.insertExpenseCategory(category)
    fun insertIncomeCategory(category: IncomeCategory) = lds.insertIncomeCategory(category)

    fun deleteIncomeCategory(categoryId: String) =  lds.deleteIncomeCategory(categoryId)
    fun deleteExpenseCategory(categoryId: String) =  lds.deleteExpenseCategory(categoryId)

    fun accountList()=lds.accountList()

    fun creditAccountList() = lds.creditAccountList()

    fun creditAccount(accountId: String) = lds.creditAccount(accountId)

    fun insertCreditAccount(account: CreditAccount) = lds.insertCreditAccount(account)

    fun deleteCreditAccount(accountId: String) =  lds.deleteCreditAccount(accountId)




}

