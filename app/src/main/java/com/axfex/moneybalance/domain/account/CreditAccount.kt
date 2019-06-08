package com.axfex.moneybalance.domain.account

import com.axfex.moneybalance.domain.category.AccountCategory
import com.axfex.moneybalance.domain.operation.Amount
import com.axfex.moneybalance.domain.currency.Currency
import java.util.*
import kotlin.collections.HashSet

class CreditAccount(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String,
    override val category: AccountCategory,
    override val owner: String,
    override val balance: Amount,
    override val currency: Currency,
    override val creationDate: Date,
    val limit: Amount? = null,
    override val currencies: HashSet<Currency> = HashSet()
) : Account(
    id, name, category, owner, balance, currency, creationDate
), MultiCurrencyAccount {
    init {
        currencies.add(currency)

    }


}