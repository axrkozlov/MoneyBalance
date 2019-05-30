package com.axfex.moneybalance.ui.welcome

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.domain.UserManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WelcomeViewModel(val userManager: UserManager) : ViewModel(), CoroutineScope {
    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    fun signInAnonimously() = launch {
        userManager.signInAnonimously()

    }

}
