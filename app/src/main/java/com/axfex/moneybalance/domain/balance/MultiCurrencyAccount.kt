package com.axfex.moneybalance.domain.balance

interface MultiCurrencyAccount {
    val currencies: HashSet<Currency>

}