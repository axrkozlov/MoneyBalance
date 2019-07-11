package com.axfex.moneybalance.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.axfex.moneybalance.domain.operation.Expense


//interface OperationsDao {
//    @Query("SELECT * FROM expenses")
//    suspend fun expenses(): List<Expense>
//
//    @Insert(onConflict = REPLACE)
//    suspend fun insertExpence(expense: Expense):Long
//
//}