package com.axfex.moneybalance.data.source.local

import android.content.SharedPreferences

class UserPrefs(private val sharedPreferences: SharedPreferences) {


    private val ONBOARDING_COMPLETE_KEY = "ONBOARDING_COMPLETE_KEY"

    private val DISPLAY_NAME = "DISPLAY_NAME"

    var isFirstStartComplete
        get() = sharedPreferences.getBoolean(ONBOARDING_COMPLETE_KEY,false)
        set(isComplete){
            sharedPreferences.edit().putBoolean(ONBOARDING_COMPLETE_KEY,isComplete).apply()
        }

    var displayUserName
        get() = sharedPreferences.getString(DISPLAY_NAME,"")
        set(displayName){
            sharedPreferences.edit().putString(DISPLAY_NAME,displayName).apply()
        }
}