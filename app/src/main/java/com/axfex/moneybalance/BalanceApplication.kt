package com.axfex.moneybalance

import android.app.Application

class BalanceApplication:Application() {

    val viewModelFactory by lazy {
        ViewModelFactory(this)
    }
}