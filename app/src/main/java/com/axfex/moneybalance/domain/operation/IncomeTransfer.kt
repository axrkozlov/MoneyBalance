package com.axfex.moneybalance.domain.operation

import com.axfex.moneybalance.domain.account.Account
import com.axfex.moneybalance.domain.category.IncomeCategory
import com.axfex.moneybalance.domain.currency.Currency
import java.util.*

data class IncomeTransfer(
    override val id: String,
    override val account: Account,
    override val amount: Amount,
    override val currency: Currency,
    override val date: Date,
    override val note: String?=null,
    override val category: IncomeCategory?=null
) : Operation(id, account, amount, currency, date, note, category)