package com.axfex.moneybalance.ui.category.dialog

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
    CategoryDialogModule.ProvideViewModel::class
])
abstract class CategoryDialogModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class,
        CategoryDialogModule.InjectAdapter::class])
    abstract fun bind(): CategoryDialogFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(CategoryDialogViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel = CategoryDialogViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: CategoryDialogFragment) =
            ViewModelProviders.of(target, factory).get(CategoryDialogViewModel::class.java)

        @Provides
        fun provideMainViewModel(target: CategoryDialogFragment) = target.activity?.run {
            ViewModelProviders.of(this)[MainViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    @Module
    class InjectAdapter {

        @Provides
        fun  provideAdapter(viewModel: CategoryDialogViewModel,mainViewModel: MainViewModel) = CategoryDialogAdapter(viewModel,mainViewModel)


    }



}