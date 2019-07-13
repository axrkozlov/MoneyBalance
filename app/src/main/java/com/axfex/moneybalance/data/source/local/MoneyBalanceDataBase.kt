package com.axfex.moneybalance.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.domain.model.category.CategoryView
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.icon.Icon

@Database(
    entities = [
        Icon::class,
        ExpenseCategory::class,
        IncomeCategory::class
    ],
    views = [CategoryView::class],
    version = 4
    , exportSchema = false
)
abstract class MoneyBalanceDataBase : RoomDatabase() {

    //    abstract fun operationsDao():OperationsDao
    abstract fun iconsDao(): IconsDao
    abstract fun categoriesDao(): CategoriesDao

}