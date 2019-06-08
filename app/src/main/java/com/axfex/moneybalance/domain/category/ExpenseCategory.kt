package com.axfex.moneybalance.domain.category


class ExpenseCategory(
    override val id: String,
    override val name: String,
    override val imageUrl: String? = null
) : Category(id,name,imageUrl)