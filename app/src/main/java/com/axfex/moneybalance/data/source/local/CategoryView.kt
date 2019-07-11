package com.axfex.moneybalance.data.source.local

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.icon.Icon
import java.util.*


@DatabaseView("SELECT *,'expense' as type FROM expenseCategory UNION SELECT *,'income' as type FROM incomeCategory")
data class CategoryView(

    val id: String,
    val name: String,
    @Embedded(prefix = "icon")
val icon: Icon,
    val type:String
)