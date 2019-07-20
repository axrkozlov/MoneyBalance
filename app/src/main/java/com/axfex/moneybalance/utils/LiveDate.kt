package com.axfex.moneybalance.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline subscriber: (T) -> Unit) {
    observe(owner, Observer { value -> subscriber(value) })
}
