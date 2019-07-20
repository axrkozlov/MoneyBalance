package com.axfex.moneybalance.domain.model.category

import androidx.room.*
import com.axfex.moneybalance.domain.converters.CategoryTypeConverter
import java.util.*

@TypeConverters(CategoryTypeConverter::class)
abstract class Category(
    @PrimaryKey
    open val id: String,
    open val name:String,
    val iconName: String,
    val color : Int,
    val type:CategoryType
) {


    override fun equals(other: Any?): Boolean {
        if(this === other)
            return true

        if(other !is Category)
            return false

        return (other.name == this.name && other.id == this.id && other.iconName==this.iconName && other.type==this.type)
    }

    override fun hashCode(): Int {
        return UUID.fromString(id).hashCode()
    }
}