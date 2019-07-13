package com.axfex.moneybalance.domain.model.account

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axfex.moneybalance.domain.model.currency.Currency
import com.axfex.moneybalance.domain.model.icon.Icon
import java.math.BigDecimal
import java.util.*

@Entity
abstract class Account(
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val name: String,
    open val icon: Icon,
    open val owner: String,
    open val balance: BigDecimal,
    open val currency: Currency,
    open val creationDate: Date
)

//{
//    constructor(
//        id: UUID,
//        name: String,
//        incomeCategory: AccountCategory,
//        owner: String,
//        balance: Amount,
//        currencies: HashSet<Currency>,
//        creationDate: Date
//    ) :
//            this(id, name, incomeCategory, owner, balance, currencies.first(), creationDate)
//}