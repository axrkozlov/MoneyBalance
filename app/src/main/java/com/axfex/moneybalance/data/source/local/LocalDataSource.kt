package com.axfex.moneybalance.data.source.local

import android.graphics.drawable.Drawable
import com.axfex.moneybalance.data.source.OperationsDataSource
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.operation.Operation
import java.math.BigDecimal


class LocalDataSource(val categoriesDao: CategoriesDao) : OperationsDataSource {

    init {

    }

    fun addOperation(operation: Operation) {

    }

    fun getOperations() {

    }

    fun addAmount(amount: BigDecimal) {

    }

    fun getAmounts(): List<BigDecimal>? {
        return null
    }

    fun addCategory(){
        categoriesDao.insert(ExpenseCategory("1","AAA",null))
    }


}