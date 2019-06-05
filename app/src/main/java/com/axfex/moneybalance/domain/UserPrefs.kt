package com.axfex.moneybalance.domain

import android.content.Context
import android.content.SharedPreferences

class UserPrefs(private val sharedPreferences: SharedPreferences) {


    private val KEY_FIRST_START = "FIRST_START_COMPLETE"

    private val DISPLAY_NAME = "DISPLAY_NAME"

    var isFirstStartComplete
        get() = sharedPreferences.getBoolean(KEY_FIRST_START,false)
        set(isComplete){
            sharedPreferences.edit().putBoolean(KEY_FIRST_START,isComplete).apply()
        }

    var displayName
        get() = sharedPreferences.getString(DISPLAY_NAME,"")
        set(displayName){
            sharedPreferences.edit().putString(DISPLAY_NAME,displayName).apply()
        }
}