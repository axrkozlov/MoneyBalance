package com.axfex.moneybalance.domain.balance

import java.util.*

data class Expence(
    val id: UUID,
    val account: Account,
    val amount: Amount,
    val currency: Currency,
    val date: Date,
    val note: String?,
    val category: ExpenseCategory
) : Transaction(id, account, amount, currency, date, note)