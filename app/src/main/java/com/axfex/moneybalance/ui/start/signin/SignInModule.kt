package com.axfex.moneybalance.ui.start.signin

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
    SignInModule.ProvideViewModel::class
])
abstract class SignInModule {

    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): SignInFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(SignInViewModel::class)
        fun provideViewModel(userManager: UserManager): ViewModel = SignInViewModel(userManager)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: SignInFragment) =
            ViewModelProviders.of(target, factory).get(SignInViewModel::class.java)
    }

}