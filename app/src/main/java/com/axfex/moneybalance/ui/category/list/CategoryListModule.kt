package com.axfex.moneybalance.ui.category.list

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
    CategoryListModule.ProvideViewModel::class
])
abstract class CategoryListModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class,
    CategoryListModule.InjectAdapter::class])
    abstract fun bind(): CategoryListFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(CategoryListViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel =
            CategoryListViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: CategoryListFragment) =
            ViewModelProviders.of(target, factory).get(CategoryListViewModel::class.java)
    }

    @Module
    class InjectAdapter {

        @Provides
        fun  provideAdapter() = CategoryListAdapter()


    }

}