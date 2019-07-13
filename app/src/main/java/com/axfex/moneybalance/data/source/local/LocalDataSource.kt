package com.axfex.moneybalance.data.source.local

import com.axfex.moneybalance.data.source.OperationsDataSource
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.icon.Icon
import com.axfex.moneybalance.domain.model.operation.Operation
import java.math.BigDecimal


class LocalDataSource(
    val iconsDao: IconsDao,
    val categoriesDao: CategoriesDao) : OperationsDataSource {

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

    fun insertIcons(vararg icons: Icon) = iconsDao.insertIcons(*icons)
    fun iconList()=iconsDao.iconList()

    fun expenseCategoryList() = categoriesDao.expenseCategoryList()
    fun incomeCategoryList() = categoriesDao.incomeCategoryList()

    fun expenseCategory(categoryId: String) = categoriesDao.expenseCategory(categoryId)
    fun incomeCategory(categoryId: String) = categoriesDao.incomeCategory(categoryId)

    fun insertExpenseCategory(category: ExpenseCategory) = categoriesDao.insertExpenseCategory(category)
    fun insertIncomeCategory(category: IncomeCategory) = categoriesDao.insertIncomeCategory(category)

    fun deleteIncomeCategory(categoryId: String) =  categoriesDao.deleteIncomeCategory(categoryId)
    fun deleteExpenseCategory(categoryId: String) =  categoriesDao.deleteExpenseCategory(categoryId)


fun categoryList()=categoriesDao.categoryList()
}