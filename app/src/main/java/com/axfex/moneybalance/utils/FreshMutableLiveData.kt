package com.axfex.moneybalance.utils

import androidx.annotation.MainThread
import androidx.arch.core.internal.SafeIterableMap
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.ref.WeakReference

/**
 * The Class provides a new data delivery only
 * WrapperObserver class prevent onChange when LiveData have an old data
 */

class FreshMutableLiveData<T> : MutableLiveData<T>() {

    private var version = -1
    private val mWrappers = SafeIterableMap<Observer<in T>, WrapperObserver>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val wrapper = WrapperObserver(owner, observer)
        val existing = mWrappers.putIfAbsent(observer, wrapper)
        if (existing != null && !existing.isAttachedTo(owner)) {
            throw IllegalArgumentException("Cannot add the same observer" + " with different lifecycles")
        }
        if (existing != null) {
            return
        }
        super.observe(owner, wrapper)
    }

    @MainThread
    override fun observeForever(observer: Observer<in T>) {
        val wrapper = WrapperObserver(observer)
        val existing = mWrappers.putIfAbsent(observer, wrapper)
        if (existing != null) {
            return
        }
        super.observeForever(observer)
    }

    override fun postValue(value: T) {
        version++
        super.postValue(value)
    }

    @MainThread
    override fun setValue(value: T) {
        version++
        super.setValue(value)
    }

    @MainThread
    override fun removeObservers(owner: LifecycleOwner) {
        for ((key, value) in mWrappers) {
            if (value.isAttachedTo(owner))
                removeObserver(key)
        }
    }
    @MainThread
    override fun removeObserver(observer: Observer<in T>) {
        val removed = mWrappers.remove(observer) ?: return
        super.removeObserver(removed)

    }

    private inner class WrapperObserver : Observer<T> {
        private var mObserver: Observer<in T>? = null
        private lateinit var mLifecycleOwnerWeakReference: WeakReference<LifecycleOwner>
        private var thisVersion: Int = 0

        internal constructor(lifecycleOwner: LifecycleOwner, observer: Observer<in T>) {
            this.mObserver = observer
            mLifecycleOwnerWeakReference = WeakReference(lifecycleOwner)
            thisVersion = version
        }


        internal constructor(observer: Observer<in T>) {
            this.mObserver = observer
            thisVersion = version
        }

        override fun onChanged(t: T?) {
            if (thisVersion != version) {
                mObserver!!.onChanged(t)
            }
            thisVersion = version
        }

        internal fun isAttachedTo(lifecycleOwner: LifecycleOwner): Boolean {
            return mLifecycleOwnerWeakReference.get() == lifecycleOwner
        }
    }
}