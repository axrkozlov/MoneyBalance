package com.axfex.moneybalance.data

import android.content.Context
import androidx.room.Room
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.data.source.local.MoneyBallanceDataBase
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.local.UserPrefs
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.IconsManager
import com.axfex.moneybalance.data.source.remote.RemoteDataSource
import com.axfex.moneybalance.domain.auth.UserManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {


    @Provides
    @Singleton
    fun provideRepository(localDataSource: LocalDataSource, remoteDataSource: RemoteDataSource, iconsManager: IconsManager): Repository =
        Repository(localDataSource,remoteDataSource,iconsManager)

    @Provides
    @Singleton
    fun provideLocalDataSource(categoriesDao: CategoriesDao): LocalDataSource =
        LocalDataSource(categoriesDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource( context:Context): RemoteDataSource =
        RemoteDataSource(context)

    @Provides
    @Singleton
    fun provideIconsManager(context:Context) =
        IconsManager(context)

//    @Provides
//    @Singleton
//    fun provideOperationsDao(database: MoneyBallanceDataBase) =
//        database.operationsDao()

    @Provides
    @Singleton
    fun provideCategoriesDao(database: MoneyBallanceDataBase) =
        database.categoriesDao()

    @Provides
    @Singleton
    fun provideUserPrefs(app: App) =
        UserPrefs(app.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE))

    @Provides
    @Singleton
    fun provideUserManager(userPrefs: UserPrefs) = UserManager(userPrefs)

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            MoneyBallanceDataBase::class.java,
            "MoneyBalance.db"
        )
            .build()
}