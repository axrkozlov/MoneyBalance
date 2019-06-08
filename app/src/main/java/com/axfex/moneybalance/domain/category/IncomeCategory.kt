package com.axfex.moneybalance.domain.category

class IncomeCategory(override val id: String,
                     override val name: String,
                     override val imageUrl: String? = null
) : Category(id,name,imageUrl)