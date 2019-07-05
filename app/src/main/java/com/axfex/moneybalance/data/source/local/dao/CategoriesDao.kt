package com.axfex.moneybalance.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.domain.category.ExpenseCategory
import java.util.*

@Dao
interface CategoriesDao{
    @Query("SELECT * FROM expenseCategory")
    fun list(): List<ExpenseCategory>

    @Insert(onConflict = REPLACE)
    fun insert(expenseCategory: ExpenseCategory): Long

}