package com.axfex.moneybalance.utils.remove

//import android.app.Application
//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.axfex.moneybalance.domain.auth.UserManager
//import com.axfex.moneybalance.ui.profile.ProfileViewModel
//import com.axfex.moneybalance.ui.balance.BalanceViewModel
//import com.axfex.moneybalance.ui.start.signin.SignInViewModel
//import com.axfex.moneybalance.ui.start.signin.ReadyViewModel
//import com.axfex.moneybalance.ui.start.splash.SplashViewModel
//import com.axfex.moneybalance.ui.start.welcome.WelcomeViewModel
//

//@Suppress("UNCHECKED_CAST")
//class ViewModelFactory(application: Application) : ViewModelProvider.Factory {
//
////    private val userManager =
////        UserManager(
////            application.getSharedPreferences("UserManager", Context.MODE_PRIVATE),
////            application.applicationContext
////        )
//
//    private val startManager= StartManager(
//        application.getSharedPreferences(
//            "StartManager",
//            Context.MODE_PRIVATE
//        )
//    )
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//
//        return when (modelClass) {
////            SplashViewModel::class.java -> SplashViewModel(startManager)
////            WelcomeViewModel::class.java -> WelcomeViewModel(userManager)
////            SignInViewModel::class.java -> SignInViewModel(userManager)
////            ReadyViewModel::class.java -> ReadyViewModel(userManager,startManager)
////            ProfileViewModel::class.java -> ProfileViewModel(userManager)
//
//            else -> throw IllegalArgumentException("No ViewModel registered for $modelClass")
//        } as T
//
//    }
//
//}