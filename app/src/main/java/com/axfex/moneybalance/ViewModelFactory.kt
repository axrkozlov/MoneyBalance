package com.axfex.moneybalance

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axfex.moneybalance.domain.UserManager
import com.axfex.moneybalance.ui.profile.ProfileViewModel
import com.axfex.moneybalance.ui.balance.BalanceViewModel
import com.axfex.moneybalance.ui.signin.SignInViewModel
import com.axfex.moneybalance.ui.signin.SignUpViewModel
import com.axfex.moneybalance.ui.splash.SplashViewModel
import com.axfex.moneybalance.ui.welcome.WelcomeViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val userManager =
        UserManager(
            application.getSharedPreferences("UserManager", Context.MODE_PRIVATE),
            application.applicationContext
        )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            SplashViewModel::class.java -> SplashViewModel(userManager)
            WelcomeViewModel::class.java -> WelcomeViewModel(userManager)
            SignInViewModel::class.java -> SignInViewModel(userManager)
            SignUpViewModel::class.java -> SignUpViewModel(userManager)
            ProfileViewModel::class.java -> ProfileViewModel(userManager)
            BalanceViewModel::class.java -> BalanceViewModel()

            else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
        } as T

    }

}