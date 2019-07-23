package com.axfex.moneybalance.ui.operation.edit

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
        EditOperationModule.ProvideViewModel::class
    ]
)
abstract class EditOperationModule {


    @ContributesAndroidInjector(
        modules = [InjectViewModel::class]
    )
    abstract fun bind(): EditOperationFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(EditOperationViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel =
            EditOperationViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: EditOperationFragment) =
            ViewModelProviders.of(target, factory).get(EditOperationViewModel::class.java)

        @Provides
        fun provideMainViewModel(target: EditOperationFragment) = target.activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

}