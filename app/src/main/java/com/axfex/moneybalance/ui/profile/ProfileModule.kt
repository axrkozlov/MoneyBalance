package com.axfex.moneybalance.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import com.axfex.moneybalance.domain.auth.UserManager
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(includes = [
    ProfileModule.ProvideViewModel::class
])
abstract class ProfileModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): ProfileFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(ProfileViewModel::class)
        fun provideViewModel(userManager: UserManager): ViewModel = ProfileViewModel(userManager)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: ProfileFragment) =
            ViewModelProviders.of(target, factory).get(ProfileViewModel::class.java)
    }

}