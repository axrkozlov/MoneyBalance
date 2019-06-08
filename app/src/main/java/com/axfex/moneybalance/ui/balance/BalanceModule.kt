package com.axfex.moneybalance.ui.balance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import com.axfex.moneybalance.data.source.Repository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(
    includes = [
        BalanceModule.ProvideViewModel::class
    ]
)
abstract class BalanceModule {


    @ContributesAndroidInjector(
        modules = [InjectViewModel::class,
            BalanceModule.InjectAdapter::class]
    )
    abstract fun bind(): BalanceFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(BalanceViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel = BalanceViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: BalanceFragment) =
            ViewModelProviders.of(target, factory).get(BalanceViewModel::class.java)
    }

    @Module
    class InjectAdapter {

        @Provides
        fun provideAdapter() = BalanceAdapter()


    }
}