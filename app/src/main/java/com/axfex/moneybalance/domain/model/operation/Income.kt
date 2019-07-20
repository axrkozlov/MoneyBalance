package com.axfex.moneybalance.domain.model.operation

import com.axfex.moneybalance.domain.model.account.Account
import com.axfex.moneybalance.domain.model.category.IncomeCategory
import com.axfex.moneybalance.domain.model.currency.Currency
import java.math.BigDecimal
import java.util.*


data class Income(
    override val id: String,
    override val account: Account,
    override val amount: BigDecimal,
    override val currency: Currency,
    override val date: Date,
    override val note: String?=null,
    override val category: IncomeCategory?=null
    ) : Operation(id, account, amount, currency, date, note, category)
