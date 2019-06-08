package com.axfex.moneybalance.domain.category

import androidx.room.PrimaryKey
import java.util.*

abstract class Category(
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val name:String,
    open val imageUrl:String?=null
)