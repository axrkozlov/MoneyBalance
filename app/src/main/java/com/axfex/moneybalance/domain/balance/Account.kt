package com.axfex.moneybalance.domain.balance

import java.util.*
import kotlin.collections.HashSet

data class Account(val id:UUID,val name:String, val category: AccountCategory, val owner:String?,val balance: Amount, val currency: Currency,val creationDate: Date){
    constructor(id:UUID, name:String, category: AccountCategory, owner:String?, balance: Amount, currencies: HashSet<Currency>,creationDate: Date) :
            this(id, name,  category, owner, balance,currencies.first(),creationDate)
}