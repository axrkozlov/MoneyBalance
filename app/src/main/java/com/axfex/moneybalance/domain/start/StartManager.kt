package com.axfex.moneybalance.domain.start

import android.content.SharedPreferences

class StartManager(private val sharedPreferences: SharedPreferences) {
    private val KEY_FIRST_START = "FIRST_START_COMPLETE"

    var isFirstStartComplete
        get() = sharedPreferences.getBoolean(KEY_FIRST_START,false)
    set(isComplete){
        sharedPreferences.edit().putBoolean(KEY_FIRST_START,isComplete).apply()
    }




}