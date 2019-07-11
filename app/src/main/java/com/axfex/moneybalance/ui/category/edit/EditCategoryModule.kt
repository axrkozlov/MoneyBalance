package com.axfex.moneybalance.ui.category.edit

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
    EditCategoryModule.ProvideViewModel::class
])
abstract class EditCategoryModule {


    @ContributesAndroidInjector(modules = [InjectViewModel::class,
    EditCategoryModule.InjectAdapter::class])
    abstract fun bind(): EditCategoryFragment

    @Module
    class ProvideViewModel {
        @Provides
        @IntoMap
        @ViewModelKey(EditCategoryViewModel::class)
        fun provideViewModel(repo: Repository): ViewModel =
            EditCategoryViewModel(repo)
    }

    @Module
    class InjectViewModel {

        @Provides
        fun provideViewModel(factory: ViewModelProvider.Factory, target: EditCategoryFragment) =
            ViewModelProviders.of(target, factory).get(EditCategoryViewModel::class.java)
    }

    @Module
    class InjectAdapter {

        @Provides
        fun  provideIconAdapter(viewModel:EditCategoryViewModel) = EditCategoryIconAdapter(viewModel)

        @Provides
        fun  provideColorAdapter() = EditCategoryColorAdapter()
    }


}