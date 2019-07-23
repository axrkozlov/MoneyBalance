package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.domain.model.operation.Operation
import com.axfex.moneybalance.domain.model.operation.OperationListView

@Dao
interface OperationsDao{

//    @Query("SELECT * FROM AccountListView")
//    fun accountList(): LiveData<List<AccountListView>>
//
//    @Query("Select * FROM creditAccount where id = :accountId")
//    fun creditAccount(accountId: String): LiveData<Account>
//
//
//    @Insert(onConflict = REPLACE)
//    fun insertAccount(account: Account): Long
//
//    @Query("DELETE FROM creditAccount where id = :accountId")
//    fun deleteAccount(accountId: String)
//
//
//
//
//    @Query("SELECT * FROM expenseCategory")
//    fun expenseCategoryList(): LiveData<List<CategoryListView>>



//    @Query("SELECT * FROM incomeCategory")
//    fun incomeList(): LiveData<List<CategoryListView>>

//    @Query("Select * FROM category where id = :categoryId")
//    fun expense(categoryId: String): LiveData<Category>

//    @Query("Select * FROM incomeCategory where id = :categoryId")
//    fun income(categoryId: String): LiveData<IncomeCategory>



//    @Query("DELETE FROM incomeCategory where id = :categoryId")
//    fun deleteIncomeCategory(categoryId: String)

    @Query("SELECT * FROM OperationListView")
    fun operationList(): LiveData<List<OperationListView>>

    @Insert(onConflict = REPLACE)
    fun insertOperation(operation: Operation): Long


}