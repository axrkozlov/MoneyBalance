package com.axfex.moneybalance.domain.model.operation

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.category.ExpenseCategory
import com.axfex.moneybalance.domain.model.icon.Icon
import java.math.BigDecimal

@Entity(tableName = "expense",
     foreignKeys = [
          ForeignKey(
               entity = Account::class,
               parentColumns = ["id"],
               childColumns = ["accountId"],
               onDelete = ForeignKey.NO_ACTION
          ),
          ForeignKey(
               entity = ExpenseCategory::class,
               parentColumns = ["id"],
               childColumns = ["expenseId"],
               onDelete = ForeignKey.NO_ACTION
          )
     ],
     indices = [Index("accountId","expenseId")]
)
data class Expense(
     @PrimaryKey
     val id: String,
     val accountId: String,
     val amount: BigDecimal,
//     val currency: Currency,
//     val date: Date,
     val note: String?=null,
     val expenseCategoryId: String
)