package com.axfex.moneybalance.domain.model.operation

import androidx.room.*
import com.axfex.moneybalance.domain.converters.MoneyTypeConverter
import com.axfex.moneybalance.domain.converters.OperationTypeConverter
import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.category.Category
import java.math.BigDecimal


@TypeConverters(OperationTypeConverter::class, MoneyTypeConverter::class)
@Entity(tableName = "operation",
     foreignKeys = [
          ForeignKey(
               entity = Account::class,
               parentColumns = ["id"],
               childColumns = ["accountId"],
               onDelete = ForeignKey.NO_ACTION
          ),
          ForeignKey(
               entity = Category::class,
               parentColumns = ["id"],
               childColumns = ["categoryId"],
               onDelete = ForeignKey.NO_ACTION
          )
     ],
     indices = [Index("accountId"),Index("categoryId")]
)
data class Operation(
     @PrimaryKey
     val id: String,
     val accountId: String,
     val amount: BigDecimal,
//     val currency: Currency,
//     val date: Date,
//     val transferAccountId: String,
     val note: String?=null,
     val categoryId: String,
     val type: OperationType
)