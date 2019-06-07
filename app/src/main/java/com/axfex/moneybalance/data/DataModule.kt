package com.axfex.moneybalance.data

import android.content.Context
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.local.UserPrefs
import com.axfex.moneybalance.data.source.remote.RemoteDataSource
import com.axfex.moneybalance.domain.auth.UserManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule() {


    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource): Repository =
        Repository(localDataSource,remoteDataSource)

    @Provides
    @Singleton
    fun provideLocalDataSource( context:Context): LocalDataSource =
        LocalDataSource(context)

    @Provides
    @Singleton
    fun provideRemoteDataSource( context:Context): RemoteDataSource =
        RemoteDataSource(context)


    @Provides
    @Singleton
    fun provideUserPrefs(app: App) =
        UserPrefs(app.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE))

    @Provides
    @Singleton
    fun provideUserManager(userPrefs: UserPrefs) = UserManager(userPrefs)


}