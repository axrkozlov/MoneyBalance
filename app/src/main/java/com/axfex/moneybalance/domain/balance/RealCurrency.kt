package com.axfex.moneybalance.domain.balance

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

data class RealCurrency (

    override val id: String= UUID.randomUUID().toString(),
    override val name:String,
    val iso: String,
    override val imageUrl:String?=null

):Currency(id,name,imageUrl)