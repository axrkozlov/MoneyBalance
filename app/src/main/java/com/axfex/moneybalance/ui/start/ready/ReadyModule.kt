package com.axfex.moneybalance.ui.start.ready

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import com.axfex.moneybalance.domain.UserPrefs
import com.axfex.moneybalance.ui.start.signin.ReadyFragment
import com.axfex.moneybalance.ui.start.signin.ReadyViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(includes = [
    ReadyModule.ProvideViewModel::class
])
abstract class ReadyModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): ReadyFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(ReadyViewModel::class)
        fun provideViewModel(userPrefs: UserPrefs): ViewModel = ReadyViewModel(userPrefs)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: ReadyFragment) =
            ViewModelProviders.of(target, factory).get(ReadyViewModel::class.java)
    }

}