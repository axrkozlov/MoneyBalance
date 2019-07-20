package com.axfex.moneybalance.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axfex.moneybalance.data.source.local.dao.AccountsDao
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.data.source.local.dao.OperationsDao
import com.axfex.moneybalance.domain.model.account.AccountListView
import com.axfex.moneybalance.domain.model.account.CreditAccount
import com.axfex.moneybalance.domain.model.category.*
import com.axfex.moneybalance.domain.model.icon.Icon

@Database(
    entities = [
        Icon::class,
        ExpenseCategory::class,
        IncomeCategory::class,
        CreditAccount::class
    ],
    views = [CategoryListView::class,
        ExpenseCategoryListView::class,
        IncomeCategoryListView::class,

    AccountListView::class
    ],
    version = 9
    , exportSchema = false
)
abstract class MoneyBalanceDataBase : RoomDatabase() {

    //    abstract fun operationsDao():OperationsDao
    abstract fun iconsDao(): IconsDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun accountsDao(): AccountsDao
    abstract fun operationsDao(): OperationsDao
}