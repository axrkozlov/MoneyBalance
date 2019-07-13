package com.axfex.moneybalance.ui.start.splash

import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.data.source.local.UserPrefs

class SplashViewModel(userPrefs: UserPrefs): ViewModel() {

    val isFirstStartComplete=userPrefs.isFirstStartComplete





}