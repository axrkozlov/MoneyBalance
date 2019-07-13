package com.axfex.moneybalance.domain.model.category

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.TypeConverters
import com.axfex.moneybalance.domain.converters.TypesConverter
import com.axfex.moneybalance.domain.model.icon.Icon

@TypeConverters(TypesConverter::class)
@Entity(
    tableName = "incomeCategory",
    foreignKeys = [
        ForeignKey(
            entity = Icon::class,
            parentColumns = ["name"],
            childColumns = ["iconName"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [Index("name")]
)
class IncomeCategory(
    id: String,
    name: String,
    iconName: String,
    color:Int,
    type: CategoryType
) : Category(
    id,
    name,
    iconName,
    color,
    type
)