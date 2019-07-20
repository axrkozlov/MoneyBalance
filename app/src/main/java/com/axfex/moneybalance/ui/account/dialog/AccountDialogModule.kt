package com.axfex.moneybalance.ui.account.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.di.ViewModelKey
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module(includes = [
    AccountDialogModule.ProvideViewModel::class
])
abstract class AccountDialogModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class,
        AccountDialogModule.InjectAdapter::class])
    abstract fun bind(): AccountDialogFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(AccountDialogViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel = AccountDialogViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: AccountDialogFragment) =
            ViewModelProviders.of(target, factory).get(AccountDialogViewModel::class.java)

        @Provides
        fun provideMainViewModel(target: AccountDialogFragment) = target.activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    @Module
    class InjectAdapter {

        @Provides
        fun  provideAdapter(viewModel: AccountDialogViewModel,mainViewModel: MainViewModel) = AccountDialogAdapter(viewModel,mainViewModel)


    }



}