package com.axfex.moneybalance.domain.model.category

import androidx.room.*
import com.axfex.moneybalance.domain.converters.CategoryTypeConverter

@DatabaseView("SELECT IncomeCategory.id,IncomeCategory.name,IncomeCategory.iconName,IncomeCategory.color" +
        " FROM IncomeCategory ")
data class IncomeCategoryListView(
    val id: String,
    val name: String,
    val iconName: String,
    val color:Int
)