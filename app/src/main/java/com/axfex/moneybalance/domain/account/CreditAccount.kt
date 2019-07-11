package com.axfex.moneybalance.domain.account

import com.axfex.moneybalance.domain.currency.Currency
import com.axfex.moneybalance.domain.icon.Icon
import java.math.BigDecimal
import java.util.*
import kotlin.collections.HashSet

class CreditAccount(
    override val id: String = UUID.randomUUID().toString(),
    override val name: String,
    override val icon: Icon,
    override val owner: String,
    override val balance: BigDecimal,
    override val currency: Currency,
    override val creationDate: Date,
    val limit: BigDecimal? = null,
    override val currencies: HashSet<Currency> = HashSet()
) : Account(
    id, name, icon, owner, balance, currency, creationDate
), MultiCurrencyAccount {
    init {
        currencies.add(currency)

    }


}