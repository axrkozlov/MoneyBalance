package com.axfex.moneybalance.ui.splash

import androidx.lifecycle.ViewModel
import com.axfex.moneybalance.domain.UserManager

class SplashViewModel(userManager: UserManager): ViewModel() {

    val currentUser=userManager.currentUser

}