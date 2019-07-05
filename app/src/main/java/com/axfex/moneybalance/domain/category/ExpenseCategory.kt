package com.axfex.moneybalance.domain.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenseCategory")
class ExpenseCategory(
     @PrimaryKey
     val id: String,
     val name: String,
     val imageUrl: String? = null
)