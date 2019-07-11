package com.axfex.moneybalance.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.category.IncomeCategory
import com.axfex.moneybalance.domain.operation.Expense

@Database(
    entities = [
        ExpenseCategory::class,
    IncomeCategory::class
    ],views = [CategoryView::class],
    version = 1
    , exportSchema = false
)
abstract class MoneyBalanceDataBase : RoomDatabase() {

//    abstract fun operationsDao():OperationsDao

    abstract fun categoriesDao(): CategoriesDao

}