package com.axfex.moneybalance.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.axfex.moneybalance.domain.model.category.Category
import com.axfex.moneybalance.domain.model.category.CategoryView
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory

@Dao
interface CategoriesDao{
    @Query("SELECT * FROM expenseCategory")
    fun expenseCategoryList(): LiveData<List<CategoryView>>

    @Query("SELECT * FROM incomeCategory")
    fun incomeCategoryList(): LiveData<List<CategoryView>>

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
    fun categoryList(): LiveData<List<CategoryView>>

}