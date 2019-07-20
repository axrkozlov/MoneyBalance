package com.axfex.moneybalance.domain.model.category

import androidx.room.*
import com.axfex.moneybalance.domain.converters.CategoryTypeConverter

@DatabaseView("SELECT ExpenseCategory.id,ExpenseCategory.name,ExpenseCategory.iconName,ExpenseCategory.color FROM ExpenseCategory ")
data class ExpenseCategoryListView(
    val id: String,
    val name: String,
    val iconName: String,
    val color:Int
)