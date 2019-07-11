package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.data.source.local.CategoryView
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.category.IncomeCategory
import java.util.*

@Dao
interface CategoriesDao{
    @Query("SELECT * FROM expenseCategory UNION SELECT * FROM incomeCategory")
    fun expenseCategoryList(): LiveData<List<Category>>

    @Query("SELECT * FROM incomeCategory")
    fun incomeCategoryList(): LiveData<List<IncomeCategory>>

    @Query("Select * FROM expenseCategory where id = :categoryId")
    fun expenseCategory(categoryId: String): LiveData<ExpenseCategory>

    @Query("Select * FROM incomeCategory where id = :categoryId")
    fun incomeCategory(categoryId: String): LiveData<IncomeCategory>

    @Insert(onConflict = REPLACE)
    fun insertExpenseCategory(category: ExpenseCategory): Long

    @Insert(onConflict = REPLACE)
    fun insertIncomeCategory(category: IncomeCategory): Long

    @Query("DELETE FROM expenseCategory where id = :categoryId")
    fun deleteExpenseCategory(categoryId: String)

    @Query("DELETE FROM incomeCategory where id = :categoryId")
    fun deleteIncomeCategory(categoryId: String)

    @Query("SELECT * FROM CategoryView")
    fun search(): LiveData<List<CategoryView>>

}