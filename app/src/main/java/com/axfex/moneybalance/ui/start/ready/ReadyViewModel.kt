package com.axfex.moneybalance.ui.start.signin

import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.data.source.local.UserPrefs
import kotlinx.coroutines.*

class ReadyViewModel(private val userPrefs: UserPrefs) : ViewModel() {
    private val viewModelJob = SupervisorJob()

    fun completeFirstStart() {
        userPrefs.isFirstStartComplete=true
    }
}
