package com.axfex.moneybalance.ui.account.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import com.axfex.moneybalance.data.source.Repository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(includes = [
    AccountListModule.ProvideViewModel::class
])
abstract class AccountListModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class,
    InjectAdapter::class])
    abstract fun bind(): AccountListFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(AccountListViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel =
            AccountListViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: AccountListFragment) =
            ViewModelProviders.of(target, factory).get(AccountListViewModel::class.java)
    }

    @Module
    class InjectAdapter {

        @Provides
        fun  provideAdapter(viewModel: AccountListViewModel) =
            AccountListAdapter(viewModel)


    }

}