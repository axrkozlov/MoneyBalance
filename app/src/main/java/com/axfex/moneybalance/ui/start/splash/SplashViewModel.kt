package com.axfex.moneybalance.ui.start.splash

import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.UserPrefs
import com.axfex.moneybalance.domain.start.StartManager

class SplashViewModel(userPrefs: UserPrefs): ViewModel() {

    val isFirstStartComplete=userPrefs.isFirstStartComplete



}