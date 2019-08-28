package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.domain.model.account.AccountListView
import com.axfex.moneybalance.domain.model.account.Account

@Dao
interface AccountsDao{

    @Query("SELECT * FROM AccountListView ")
    fun accountList(): LiveData<List<AccountListView>>

    @Query("Select * FROM account where id = :accountId")
    fun account(accountId: String): LiveData<Account>

    @Query("Select * FROM account limit 1")
    fun getOneAccount(): Account




    @Insert(onConflict = REPLACE)
    fun insertAccount(vararg account: Account)

    @Query("DELETE FROM account where id = :accountId")
    fun deleteAccount(accountId: String)

}