package com.axfex.moneybalance.domain.model.operation

import androidx.room.*
import com.axfex.moneybalance.domain.converters.CategoryTypeConverter
import com.axfex.moneybalance.domain.converters.MoneyTypeConverter
import com.axfex.moneybalance.domain.converters.OperationTypeConverter
import java.math.BigDecimal

@TypeConverters(OperationTypeConverter::class, MoneyTypeConverter::class)
@DatabaseView("SELECT *, category.name AS categoryName, category.iconName AS categoryIconName, category.color AS categoryColor FROM operation INNER JOIN category ON operation.categoryId = category.id")
data class OperationListView(
    val id: String,
    val accountId: String,
    val amount: BigDecimal,
//     val currency: Currency,
//     val date: Date,
    val note: String? = null,
    val categoryId: String,
    val type: OperationType,
    val categoryName: String,
    val categoryIconName: String,
    val categoryColor: Int
)