package com.axfex.moneybalance.utils

import android.view.View
import android.widget.EditText

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}
fun View.invisible() {
    visibility = View.INVISIBLE
}



fun View.disable(){
    this.isEnabled=false
}
fun View.enable(){
    this.isEnabled=true
}