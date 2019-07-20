package com.axfex.moneybalance.domain.converters

import android.util.Log
import androidx.room.TypeConverter
import java.math.BigDecimal

class MoneyTypeConverter {


    @TypeConverter
    fun toBucks(cents: Long): BigDecimal {
        val converted = BigDecimal.valueOf(cents).movePointLeft(2)
        Log.i("MoneyTypeConverter", "cents: $cents -> $converted")
        return BigDecimal.valueOf(cents).movePointLeft(2)

    }

    @TypeConverter
    fun toCents(bucks: BigDecimal): Long {
        val converted = bucks.movePointRight(2).toLong()
        Log.i("MoneyTypeConverter", "amount: $bucks -> $converted")
        return bucks.movePointRight(2).toLong()
    }

}