package com.axfex.moneybalance.utils

import java.math.BigDecimal
import java.text.DecimalFormat

fun BigDecimal.format():String {
    val bd = this.setScale(2, BigDecimal.ROUND_HALF_UP)
    val df = DecimalFormat()
    df.maximumFractionDigits=2
    df.minimumFractionDigits=2
    df.isGroupingUsed = false

    return df.format(bd)
}