package com.axfex.moneybalance.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.axfex.moneybalance.core.AppViewModelFactory
import com.axfex.moneybalance.ui.account.dialog.AccountDialogModule
import com.axfex.moneybalance.ui.account.edit.EditAccountModule
import com.axfex.moneybalance.ui.account.list.AccountListModule
import com.axfex.moneybalance.ui.operation.edit.EditOperationModule
import com.axfex.moneybalance.ui.operation.list.OperationListModule
import com.axfex.moneybalance.ui.category.list.CategoryListModule
import com.axfex.moneybalance.ui.category.dialog.CategoryDialogModule
import com.axfex.moneybalance.ui.category.edit.EditCategoryModule
import com.axfex.moneybalance.ui.main.MainModule
import com.axfex.moneybalance.ui.profile.ProfileModule
import com.axfex.moneybalance.ui.start.ready.ReadyModule
import com.axfex.moneybalance.ui.start.signin.SignInModule
import com.axfex.moneybalance.ui.start.splash.SplashModule
import com.axfex.moneybalance.ui.start.welcome.WelcomeModule
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
    (
    includes = [
        MainModule::class,
        SplashModule::class,
        SignInModule::class,
        WelcomeModule::class,
        ReadyModule::class,
        OperationListModule::class,
        EditOperationModule::class,
        ProfileModule::class,
        CategoryListModule::class,
        EditCategoryModule::class,
        CategoryDialogModule::class,
        AccountListModule::class,
        AccountDialogModule::class,
        EditAccountModule::class


    ]
)
class UiModule {
    @Provides
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory =
        AppViewModelFactory(providers)
}