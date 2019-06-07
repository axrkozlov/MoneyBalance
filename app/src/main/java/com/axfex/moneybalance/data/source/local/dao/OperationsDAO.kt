package com.axfex.moneybalance.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.axfex.moneybalance.domain.balance.Income
import com.axfex.moneybalance.domain.balance.Operation

@Dao
interface OperationsDAO {
    @Query("SELECT * FROM income")
    suspend fun getIncome(): List<Income>

    @Insert(onConflict = REPLACE)
    suspend fun insertOperation(operation:Operation):Int




}