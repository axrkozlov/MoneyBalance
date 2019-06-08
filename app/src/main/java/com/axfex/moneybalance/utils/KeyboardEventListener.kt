package com.axfex.moneybalance.utils

import android.graphics.Rect
import android.util.Log
import android.view.ViewTreeObserver
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class KeyboardEventListener(
    private val activity: FragmentActivity,
    private val lifecycle: Lifecycle,
    private val callback: (isOpen: Boolean) -> Unit
) : LifecycleObserver {

    var registered=false

    private val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
        private var lastState: Boolean = activity.isKeyboardOpen()
        override fun onGlobalLayout() {
            val isOpen = activity.isKeyboardOpen()
            if (isOpen == lastState) {
                return
            } else {
                dispatchKeyboardEvent(isOpen)
                lastState = isOpen
            }
        }

    }

    init {
        // Dispatch the current signinState of the keyboard
        dispatchKeyboardEvent(activity.isKeyboardOpen())
        // Make the component lifecycle aware
        lifecycle.addObserver(this)
        registerKeyboardListener()
    }

    private fun dispatchKeyboardEvent(isOpen: Boolean) {
        when {
            isOpen  -> callback(true)
            !isOpen -> callback(false)
        }
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_RESUME)
    @CallSuper
    fun onLifecycleResume() {
        registerKeyboardListener()
    }

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    @CallSuper
    fun onLifecyclePause() {
        unregisterKeyboardListener()
    }

    private fun registerKeyboardListener() {
        if (registered) return
        activity.getRootView().viewTreeObserver.addOnGlobalLayoutListener(listener)
        registered=true
    }

    private fun unregisterKeyboardListener() {
        if (!registered) return
        activity.getRootView().viewTreeObserver.removeOnGlobalLayoutListener(listener)
        registered=false
    }
}