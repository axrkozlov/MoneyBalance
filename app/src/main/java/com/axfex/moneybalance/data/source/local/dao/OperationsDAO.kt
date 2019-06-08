package com.axfex.moneybalance.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.axfex.moneybalance.domain.operation.Income
import com.axfex.moneybalance.domain.operation.Operation

@Dao
interface OperationsDAO {
    @Query("SELECT * FROM operation")
    suspend fun getIncome(): List<Income>

    @Insert(onConflict = REPLACE)
    suspend fun insertOperation(operation:Operation):Int




}