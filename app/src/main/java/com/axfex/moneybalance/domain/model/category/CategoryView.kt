package com.axfex.moneybalance.domain.model.category

import androidx.room.*
import com.axfex.moneybalance.domain.converters.TypesConverter
import com.axfex.moneybalance.domain.model.icon.Icon

@TypeConverters(TypesConverter::class)
@DatabaseView("SELECT * FROM expenseCategory " +
        "UNION SELECT * FROM incomeCategory")
data class CategoryView(
    val id: String,
    val name: String,
    val iconName: String,
    val color:Int,
    val type: CategoryType
)