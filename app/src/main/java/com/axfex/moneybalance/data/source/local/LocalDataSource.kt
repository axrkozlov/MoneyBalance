package com.axfex.moneybalance.data.source.local

import androidx.lifecycle.LiveData
import com.axfex.moneybalance.data.source.OperationsDataSource
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.category.IncomeCategory
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

//    fun categoryList() : LiveData<List<ExpenseCategory>>{
//        return null
//    }

    fun expenseCategoryList() = categoriesDao.expenseCategoryList()
    fun incomeCategoryList() = categoriesDao.incomeCategoryList()

    fun expenseCategory(categoryId: String) = categoriesDao.expenseCategory(categoryId)
    fun incomeCategory(categoryId: String) = categoriesDao.incomeCategory(categoryId)

    fun insertExpenseCategory(category: ExpenseCategory) = categoriesDao.insertExpenseCategory(category)
    fun insertIncomeCategory(category: IncomeCategory) = categoriesDao.insertIncomeCategory(category)

    fun deleteIncomeCategory(categoryId: String) =  categoriesDao.deleteIncomeCategory(categoryId)
    fun deleteExpenseCategory(categoryId: String) =  categoriesDao.deleteExpenseCategory(categoryId)

fun search()=categoriesDao.search()
}