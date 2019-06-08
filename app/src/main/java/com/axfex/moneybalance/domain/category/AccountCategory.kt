package com.axfex.moneybalance.domain.category

data class AccountCategory(
    override val id: String,
    override val name: String,
    override val imageUrl: String? = null
) : Category(id,name,imageUrl)