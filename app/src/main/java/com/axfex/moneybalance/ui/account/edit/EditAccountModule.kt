package com.axfex.moneybalance.ui.account.edit

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
    EditAccountModule.ProvideViewModel::class
])
abstract class EditAccountModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class,
    EditAccountModule.InjectAdapter::class])
    abstract fun bind(): EditAccountFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(EditAccountViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel =
            EditAccountViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: EditAccountFragment) =
            ViewModelProviders.of(target, factory).get(EditAccountViewModel::class.java)
    }

    @Module
    class InjectAdapter {

        @Provides
        fun  provideIconAdapter(viewModel:EditAccountViewModel) = EditAccountIconAdapter(viewModel)

        @Provides
        fun  provideColorAdapter() = EditAccountColorAdapter()
    }


}