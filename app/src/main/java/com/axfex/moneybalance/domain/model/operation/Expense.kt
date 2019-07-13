package com.axfex.moneybalance.domain.model.operation

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")

data class Expense(
     @PrimaryKey
     val id: String
//     val account: Account,
//     val amount: BigDecimal,
//     val currency: Currency,
//     val date: Date,
//     val note: String?=null,
//     val incomeCategory: ExpenseCategory?=null
){
     override fun equals(other: Any?): Boolean {
          return super.equals(other)
     }
}