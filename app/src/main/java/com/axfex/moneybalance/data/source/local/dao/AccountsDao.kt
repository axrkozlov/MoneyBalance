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
interface AccountsDao{

    @Query("SELECT * FROM AccountListView ")
    fun accountList(): LiveData<List<AccountListView>>

    @Query("SELECT * FROM creditAccount")
    fun creditAccountList(): LiveData<List<CreditAccount>>

    @Query("Select * FROM creditAccount where id = :accountId")
    fun creditAccount(accountId: String): LiveData<CreditAccount>


    @Insert(onConflict = REPLACE)
    fun insertCreditAccount(vararg account: CreditAccount)

    @Query("DELETE FROM creditAccount where id = :accountId")
    fun deleteCreditAccount(accountId: String)

}