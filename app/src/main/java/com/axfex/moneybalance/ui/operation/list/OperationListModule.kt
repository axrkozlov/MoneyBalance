package com.axfex.moneybalance.ui.operation.list

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
        OperationListModule.ProvideViewModel::class
    ]
)
abstract class OperationListModule {


    @ContributesAndroidInjector(
        modules = [InjectViewModel::class,
            InjectAdapter::class]
    )
    abstract fun bind(): OperationListFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(OperationListViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel =
            OperationListViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: OperationListFragment) =
            ViewModelProviders.of(target, factory).get(OperationListViewModel::class.java)
    }

    @Module
    class InjectAdapter {

        @Provides
        fun provideAdapter(viewModel: OperationListViewModel) =
            OperationListAdapter(viewModel)


    }
}