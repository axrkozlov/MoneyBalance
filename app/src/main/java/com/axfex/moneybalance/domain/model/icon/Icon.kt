package com.axfex.moneybalance.domain.model.icon

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity
data class Icon(
    @PrimaryKey
    val name: String,
    val sortOrder:Int
)