package com.axfex.moneybalance.domain.balance

import java.util.*

abstract class Transaction(
    id:UUID,
    account: Account,
    amount: Amount,
    currency: Currency,
    date: Date,
    note: String?
)