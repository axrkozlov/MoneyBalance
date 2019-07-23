package com.axfex.moneybalance.domain.model.category

import androidx.room.*
import androidx.room.ForeignKey.NO_ACTION
import com.axfex.moneybalance.domain.converters.CategoryTypeConverter
import com.axfex.moneybalance.domain.model.icon.Icon

@TypeConverters(CategoryTypeConverter::class)
@Entity(
    tableName = "category",
    foreignKeys = [
        ForeignKey(
            entity = Icon::class,
            parentColumns = ["name"],
            childColumns = ["iconName"],
            onDelete = NO_ACTION
        )
    ],
    indices = [Index("iconName")]
)
data class Category(
    @PrimaryKey
    val id: String,
    val name: String,
    val iconName: String,
    val color:Int,
    val type: CategoryType
)