package com.axfex.moneybalance.data

import com.axfex.moneybalance.data.source.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(): Repository =
        Repository()
}