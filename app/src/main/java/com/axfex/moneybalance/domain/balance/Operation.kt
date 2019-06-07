package com.axfex.moneybalance.domain.balance


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
abstract class Operation(
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val account: Account,
    open val amount: Amount,
    open val currency: Currency =account.currency,
    open val date: Date,
    open val note: String?=null,
    open val category: Category?=null
)