package com.axfex.moneybalance.ui.start.welcome

import androidx.lifecycle.ViewModel;
import com.axfex.moneybalance.domain.UserPrefs
import com.axfex.moneybalance.domain.auth.UserManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class WelcomeViewModel(val userPrefs: UserPrefs) : ViewModel() {

}
