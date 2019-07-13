package com.axfex.moneybalance.domain.model.account

import com.axfex.moneybalance.domain.model.currency.Currency

interface MultiCurrencyAccount {
    val currencies: HashSet<Currency>

}