package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.domain.model.account.AccountListView
import com.axfex.moneybalance.domain.model.account.CreditAccount
import com.axfex.moneybalance.domain.model.category.CategoryListView
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory

@Dao
interface OperationsDao{

//    @Query("SELECT * FROM AccountListView")
//    fun accountList(): LiveData<List<AccountListView>>
//
//    @Query("Select * FROM creditAccount where id = :accountId")
//    fun creditAccount(accountId: String): LiveData<CreditAccount>
//
//
//    @Insert(onConflict = REPLACE)
//    fun insertCreditAccount(account: CreditAccount): Long
//
//    @Query("DELETE FROM creditAccount where id = :accountId")
//    fun deleteCreditAccount(accountId: String)
//
//
//
//
//    @Query("SELECT * FROM expenseCategory")
//    fun expenseCategoryList(): LiveData<List<CategoryListView>>



    @Query("SELECT * FROM incomeCategory")
    fun incomeList(): LiveData<List<CategoryListView>>

    @Query("Select * FROM expenseCategory where id = :categoryId")
    fun expense(categoryId: String): LiveData<ExpenseCategory>

    @Query("Select * FROM incomeCategory where id = :categoryId")
    fun income(categoryId: String): LiveData<IncomeCategory>



    @Query("DELETE FROM incomeCategory where id = :categoryId")
    fun deleteIncomeCategory(categoryId: String)

    @Query("SELECT * FROM CategoryListView")
    fun categoryList(): LiveData<List<CategoryListView>>

}