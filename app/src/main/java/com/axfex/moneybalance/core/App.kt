package com.axfex.moneybalance.core

import android.app.Application
import androidx.fragment.app.Fragment
import com.axfex.moneybalance.di.AppComponent
import com.axfex.moneybalance.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App:Application(), HasSupportFragmentInjector {
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
        component.inject(this)
    }

}
