package com.axfex.moneybalance.domain.model.category

import androidx.room.*
import com.axfex.moneybalance.domain.converters.CategoryTypeConverter

@TypeConverters(CategoryTypeConverter::class)
@DatabaseView("SELECT * FROM expenseCategory " +
        "UNION SELECT * FROM incomeCategory")
data class CategoryListView(
    val id: String,
    val name: String,
    val iconName: String,
    val color:Int,
    val type: CategoryType
)