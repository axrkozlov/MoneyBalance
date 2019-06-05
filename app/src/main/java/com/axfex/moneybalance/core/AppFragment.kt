package com.axfex.moneybalance.core

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.core.AppViewModelFactory
import dagger.android.support.AndroidSupportInjection

abstract class AppFragment:Fragment() {

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }
}