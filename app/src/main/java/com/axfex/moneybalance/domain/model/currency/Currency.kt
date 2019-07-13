package com.axfex.moneybalance.domain.model.currency

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
abstract class Currency (
    @PrimaryKey
    open val id: String= UUID.randomUUID().toString(),
    open val name:String,
    open val imageUrl:String?=null
)