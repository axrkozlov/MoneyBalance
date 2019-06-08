package com.axfex.moneybalance.ui.category

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
    CategoryEditModule.ProvideViewModel::class
])
abstract class CategoryEditModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class])
    abstract fun bind(): CategoryEditFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(CategoryEditViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel = CategoryEditViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: CategoryEditFragment) =
            ViewModelProviders.of(target, factory).get(CategoryEditViewModel::class.java)
    }

}