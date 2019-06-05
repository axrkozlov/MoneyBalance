package com.axfex.moneybalance.di

import android.content.Context
import com.axfex.moneybalance.core.App
import com.axfex.moneybalance.data.DataModule
import com.axfex.moneybalance.ui.UiModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        UiModule::class,
        DataModule::class
    ]
)
interface AppComponent {
    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: App): Builder


        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}