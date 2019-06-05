package com.axfex.moneybalance.ui.start.welcome

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
    WelcomeModule.ProvideViewModel::class
])
abstract class WelcomeModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): WelcomeFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(WelcomeViewModel::class)
        fun provideViewModel(userPrefs: UserPrefs): ViewModel = WelcomeViewModel(userPrefs)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: WelcomeFragment) =
            ViewModelProviders.of(target, factory).get(WelcomeViewModel::class.java)
    }

}