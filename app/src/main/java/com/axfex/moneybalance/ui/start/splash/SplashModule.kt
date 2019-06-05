package com.axfex.moneybalance.ui.start.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import com.axfex.moneybalance.domain.UserPrefs
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(includes = [
    SplashModule.ProvideViewModel::class
])
abstract class SplashModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): SplashFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(SplashViewModel::class)
        fun provideViewModel(userPrefs: UserPrefs): ViewModel = SplashViewModel(userPrefs)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: SplashFragment) =
            ViewModelProviders.of(target, factory).get(SplashViewModel::class.java)
    }

}