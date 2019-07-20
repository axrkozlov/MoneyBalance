package com.axfex.moneybalance.domain.model.account


import com.axfex.moneybalance.domain.model.currency.Currency

import java.math.BigDecimal



abstract class Account(

    open val id: String,
    open val name: String,
    open val iconName: String,
    open val color: Int,
    open val owner: String,
    open val balance: BigDecimal,
    open val currency: Currency
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