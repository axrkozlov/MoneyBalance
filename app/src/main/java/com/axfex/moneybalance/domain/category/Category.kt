package com.axfex.moneybalance.domain.category

import androidx.room.*
import com.axfex.moneybalance.domain.icon.Icon
import java.util.*

@Entity
open class Category(
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val name:String,
    @Embedded(prefix = "icon")
    val icon: Icon
) {


    override fun equals(other: Any?): Boolean {
        if(this === other)
            return true

        if(other !is Category)
            return false

        return (other.name == this.name && other.id == this.id && other.icon==this.icon)
    }

    override fun hashCode(): Int {
        return UUID.fromString(id).hashCode()
    }
}