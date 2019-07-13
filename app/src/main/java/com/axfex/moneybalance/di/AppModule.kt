package com.axfex.moneybalance.di

import android.content.Context
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.local.UserPrefs
import com.axfex.moneybalance.domain.model.auth.UserManager
import com.axfex.moneybalance.domain.model.icon.IconsManager
import com.axfex.moneybalance.domain.start.FirstStartManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideFirstStartManager(iconsManager: IconsManager, lds: LocalDataSource) =
        FirstStartManager(iconsManager,lds)

    @Provides
    @Singleton
    fun provideIconsManager(context: Context) =
        IconsManager(context)

    @Provides
    @Singleton
    fun provideUserPrefs(app: App) =
        UserPrefs(app.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE))

    @Provides
    @Singleton
    fun provideUserManager(userPrefs: UserPrefs) =
        UserManager(userPrefs)
}