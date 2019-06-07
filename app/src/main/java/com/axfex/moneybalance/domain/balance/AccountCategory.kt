package com.axfex.moneybalance.domain.balance

import java.util.*

data class AccountCategory(
    override val id: String,
    override val name: String,
    override val imageUrl: String? = null
) : Category(id,name,imageUrl)