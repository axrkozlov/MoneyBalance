package com.axfex.moneybalance.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.axfex.moneybalance.BalanceApplication


val Fragment.application
    get() = requireActivity().application as BalanceApplication

inline fun <reified VM : ViewModel> Fragment.getViewModel(): VM {
    return ViewModelProviders.of(this, application.viewModelFactory)[VM::class.java]
}