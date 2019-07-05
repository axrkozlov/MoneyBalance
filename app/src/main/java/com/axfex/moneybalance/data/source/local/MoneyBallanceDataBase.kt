package com.axfex.moneybalance.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.operation.Expense

@Database(
    entities = [
        ExpenseCategory::class
    ],
    version = 1
)
abstract class MoneyBallanceDataBase:RoomDatabase() {

//    abstract fun operationsDao():OperationsDao

    abstract fun categoriesDao():CategoriesDao

}