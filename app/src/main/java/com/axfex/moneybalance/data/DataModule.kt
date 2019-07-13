package com.axfex.moneybalance.data

import android.content.Context
import androidx.room.Room
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.data.source.Repository
import com.axfex.moneybalance.data.source.local.MoneyBalanceDataBase
import com.axfex.moneybalance.data.source.local.LocalDataSource
import com.axfex.moneybalance.data.source.local.UserPrefs
import com.axfex.moneybalance.data.source.local.dao.CategoriesDao
import com.axfex.moneybalance.data.source.local.dao.IconsDao
import com.axfex.moneybalance.domain.model.icon.IconsManager
import com.axfex.moneybalance.data.source.remote.RemoteDataSource
import com.axfex.moneybalance.domain.model.auth.UserManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {


    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        iconsManager: IconsManager
    ): Repository =
        Repository(localDataSource, remoteDataSource, iconsManager)

    @Provides
    @Singleton
    fun provideLocalDataSource(iconsDao: IconsDao,
                               categoriesDao: CategoriesDao): LocalDataSource =
        LocalDataSource(iconsDao, categoriesDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(context: Context): RemoteDataSource =
        RemoteDataSource(context)


//    @Provides
//    @Singleton
//    fun provideOperationsDao(database: MoneyBalanceDataBase) =
//        database.operationsDao()

    @Provides
    @Singleton
    fun provideCategoriesDao(database: MoneyBalanceDataBase) =
        database.categoriesDao()

    @Provides
    @Singleton
    fun provideIconsDao(database: MoneyBalanceDataBase) =
        database.iconsDao()



    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            MoneyBalanceDataBase::class.java,
            "MoneyBalance.db"
        )
            .fallbackToDestructiveMigration()
            .build()
}