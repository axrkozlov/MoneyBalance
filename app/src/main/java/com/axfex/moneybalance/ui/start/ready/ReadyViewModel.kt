package com.axfex.moneybalance.ui.start.signin

import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.UserPrefs
import com.axfex.moneybalance.domain.start.StartManager
import com.axfex.moneybalance.domain.auth.UserManager
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
class ReadyViewModel(private val userPrefs: UserPrefs) : ViewModel() {
    private val viewModelJob = SupervisorJob()

    fun completeFirstStart() {
        userPrefs.isFirstStartComplete=true
    }
}
