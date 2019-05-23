package com.axfex.moneybalance.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline subscriber: (T) -> Unit) {
    observe(owner, object : Observer<T> {
        override fun onChanged(value: T?) {
            subscriber(value!!)
        }
    })
}