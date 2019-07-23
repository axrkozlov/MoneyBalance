package com.axfex.moneybalance.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.axfex.moneybalance.data.source.local.dao.AccountsDao
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.data.source.local.dao.OperationsDao
import com.axfex.moneybalance.domain.model.account.AccountListView
import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.category.*
import com.axfex.moneybalance.domain.model.icon.Icon
import com.axfex.moneybalance.domain.model.operation.Operation
import com.axfex.moneybalance.domain.model.operation.OperationListView

@Database(
    entities = [
        Icon::class,
        Category::class,
        Account::class,
        Operation::class
    ],
    views = [CategoryListView::class,

        AccountListView::class,
        OperationListView::class
    ],
    version = 14
    , exportSchema = false
)
abstract class MoneyBalanceDataBase : RoomDatabase() {

    //    abstract fun operationsDao():OperationsDao
    abstract fun iconsDao(): IconsDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun accountsDao(): AccountsDao
    abstract fun operationsDao(): OperationsDao
}