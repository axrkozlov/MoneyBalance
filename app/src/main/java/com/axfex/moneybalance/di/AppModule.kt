package com.axfex.moneybalance.di

import android.content.Context
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.domain.UserPrefs
import com.axfex.moneybalance.domain.auth.UserManager
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideUserPrefs(app: App) = UserPrefs(app.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE))

    @Provides
    fun provideUserManager(userPrefs: UserPrefs) = UserManager(userPrefs)

}