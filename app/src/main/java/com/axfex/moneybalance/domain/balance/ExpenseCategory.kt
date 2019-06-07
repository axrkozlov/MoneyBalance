package com.axfex.moneybalance.domain.balance

import java.util.*


class ExpenseCategory(
    override val id: String,
    override val name: String,
    override val imageUrl: String? = null
) : Category(id,name,imageUrl)