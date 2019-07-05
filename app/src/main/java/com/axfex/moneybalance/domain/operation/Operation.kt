package com.axfex.moneybalance.domain.operation


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axfex.moneybalance.domain.account.Account
import com.axfex.moneybalance.domain.category.Category
import com.axfex.moneybalance.domain.currency.Currency
import java.math.BigDecimal
import java.util.*


abstract class Operation(
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val account: Account,
    open val amount: BigDecimal,
    open val currency: Currency =account.currency,
    open val date: Date,
    open val note: String?=null,
    open val category: Category?=null
)