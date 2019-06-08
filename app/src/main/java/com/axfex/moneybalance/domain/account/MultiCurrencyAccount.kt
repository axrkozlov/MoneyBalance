package com.axfex.moneybalance.domain.account

import com.axfex.moneybalance.domain.currency.Currency

interface MultiCurrencyAccount {
    val currencies: HashSet<Currency>

}