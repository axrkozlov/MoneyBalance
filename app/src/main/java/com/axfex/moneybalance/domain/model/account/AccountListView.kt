package com.axfex.moneybalance.domain.model.account

import androidx.room.*
import com.axfex.moneybalance.domain.converters.MoneyTypeConverter
import java.math.BigDecimal

@TypeConverters(MoneyTypeConverter::class)
@DatabaseView("SELECT * FROM creditAccount")
data class AccountListView(
    val id: String,
    val name: String,
    val iconName: String,
    val color:Int,
    val balance: BigDecimal
)