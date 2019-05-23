package com.axfex.moneybalance

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axfex.moneybalance.domain.UserManager
import com.axfex.moneybalance.ui.login.LoginViewModel
import com.axfex.moneybalance.ui.login.SignUpViewModel
import com.axfex.moneybalance.ui.splash.SplashViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(application: Application) : ViewModelProvider.Factory {

    private val userManager =
        UserManager(application.getSharedPreferences("UserManager", Context.MODE_PRIVATE))

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return when (modelClass) {
            SplashViewModel::class.java -> SplashViewModel(userManager)
            LoginViewModel::class.java -> LoginViewModel(userManager)
            SignUpViewModel::class.java -> SignUpViewModel(userManager)
            else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
        } as T

    }

}