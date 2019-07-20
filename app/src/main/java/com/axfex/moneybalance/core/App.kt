package com.axfex.moneybalance.core

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.fragment.app.Fragment
import com.axfex.moneybalance.data.source.local.UserPrefs
import com.axfex.moneybalance.di.AppComponent
import com.axfex.moneybalance.di.DaggerAppComponent
import com.axfex.moneybalance.domain.start.FirstStartManager
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App:Application(), HasSupportFragmentInjector, HasActivityInjector {
    override fun activityInjector(): AndroidInjector<Activity> =activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    private val FIRST_START_COMPLETE_KEY = "ONBOARDING_COMPLETE_KEY"

    private val globalPrefs:SharedPreferences by lazy {getSharedPreferences("GlobalPrefs", Context.MODE_PRIVATE)}

    var splashShowed=false

    var isFirstStartComplete
        get() = globalPrefs.getBoolean(FIRST_START_COMPLETE_KEY,false)
        set(isComplete){
            globalPrefs.edit().putBoolean(FIRST_START_COMPLETE_KEY,isComplete).apply()
        }

    @Inject
    lateinit var firstStartManager: FirstStartManager

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
        component.inject(this)

        if (!isFirstStartComplete) {
            firstStartManager.populateDb()
            isFirstStartComplete=true
        }

    }

}
