package com.axfex.moneybalance.domain.category

import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.axfex.moneybalance.domain.icon.Icon

@Entity(tableName = "expenseCategory")
class ExpenseCategory(
    id: String,
    name: String,
    icon: Icon
) : Category(
    id,
    name,
    icon
)