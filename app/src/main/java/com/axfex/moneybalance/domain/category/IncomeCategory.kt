package com.axfex.moneybalance.domain.category

import androidx.room.Entity
import com.axfex.moneybalance.domain.icon.Icon

@Entity(tableName = "incomeCategory")
class IncomeCategory
    (
                     id: String,
                     name: String,
                     icon: Icon
) : Category(
    id,
    name,
    icon
)