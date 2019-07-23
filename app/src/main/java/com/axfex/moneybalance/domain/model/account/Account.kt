package com.axfex.moneybalance.domain.model.account

import androidx.room.*
import com.axfex.moneybalance.domain.converters.MoneyTypeConverter
import com.axfex.moneybalance.domain.model.currency.Currency
import com.axfex.moneybalance.domain.model.icon.Icon
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashSet

@TypeConverters(MoneyTypeConverter::class)
@Entity(
    tableName = "account",
    foreignKeys = [
        ForeignKey(
            entity = Icon::class,
            parentColumns = ["name"],
            childColumns = ["iconName"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [Index("iconName")]
)
class Account(
    @PrimaryKey
    val id: String,
    val name: String,
    val iconName: String,
    val color: Int,
//    val owner: String,
    val balance: BigDecimal
//    val limit: BigDecimal
//    val currency: Currency
)