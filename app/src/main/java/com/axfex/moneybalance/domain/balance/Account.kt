package com.axfex.moneybalance.domain.balance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
abstract class Account(
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val name: String,
    open val category: AccountCategory,
    open val owner: String,
    open val balance: Amount,
    open val currency: Currency,
    open val creationDate: Date
)

//{
//    constructor(
//        id: UUID,
//        name: String,
//        category: AccountCategory,
//        owner: String,
//        balance: Amount,
//        currencies: HashSet<Currency>,
//        creationDate: Date
//    ) :
//            this(id, name, category, owner, balance, currencies.first(), creationDate)
//}