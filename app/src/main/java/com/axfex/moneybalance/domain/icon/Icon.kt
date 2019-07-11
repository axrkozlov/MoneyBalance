package com.axfex.moneybalance.domain.icon

import android.graphics.drawable.Drawable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Ignore

data class Icon(
    val name: String,
    var backgroundColor : Int
){

    @Ignore
    var sortOrder:Int? = null

//    @Ignore
//    var drawable:Drawable? = null

}