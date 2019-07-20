package com.axfex.moneybalance.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(
    includes = [
        MainModule.ProvideViewModel::class
    ]
)
abstract class MainModule {


    @ContributesAndroidInjector(
        modules = [InjectViewModel::class]
    )
    abstract fun bind(): MainActivity

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(MainViewModel::class)
        fun provideViewModel(): ViewModel = MainViewModel()
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: MainActivity) =
            ViewModelProviders.of(target, factory).get(MainViewModel::class.java)
    }

}