package com.axfex.moneybalance.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axfex.moneybalance.core.AppViewModelFactory
import com.axfex.moneybalance.ui.balance.BalanceModule
import com.axfex.moneybalance.ui.profile.ProfileModule
import com.axfex.moneybalance.ui.start.ready.ReadyModule
import com.axfex.moneybalance.ui.start.signin.SignInModule
import com.axfex.moneybalance.ui.start.splash.SplashModule
import com.axfex.moneybalance.ui.start.welcome.WelcomeModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
    (
    includes = [
        SplashModule::class,
        SignInModule::class,
        WelcomeModule::class,
        ReadyModule::class,
        BalanceModule::class,
        ProfileModule::class

    ]
)
class UiModule {
    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)
}