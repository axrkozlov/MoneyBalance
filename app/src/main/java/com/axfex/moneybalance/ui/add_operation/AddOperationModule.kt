package com.axfex.moneybalance.ui.add_operation

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


@Module(
    includes = [
        AddOperationModule.ProvideViewModel::class
    ]
)
abstract class AddOperationModule {


    @ContributesAndroidInjector(
        modules = [InjectViewModel::class]
    )
    abstract fun bind(): AddOperationFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(AddOperationViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel = AddOperationViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: AddOperationFragment) =
            ViewModelProviders.of(target, factory).get(AddOperationViewModel::class.java)

        @Provides
        fun provideMainViewModel(target: AddOperationFragment) = target.activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

}