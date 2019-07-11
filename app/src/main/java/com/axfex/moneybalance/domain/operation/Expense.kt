package com.axfex.moneybalance.domain.operation

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axfex.moneybalance.domain.account.Account
import com.axfex.moneybalance.domain.category.ExpenseCategory
import com.axfex.moneybalance.domain.currency.Currency
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.util.*

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