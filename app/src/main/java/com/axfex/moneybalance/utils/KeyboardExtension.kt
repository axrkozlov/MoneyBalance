package com.axfex.moneybalance.utils

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.util.TypedValue
import android.view.View

import androidx.fragment.app.Fragment

fun Activity.getRootView(): View {
    return findViewById<View>(android.R.id.content)
}
fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        this.resources.displayMetrics
    )
}
fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    this.getRootView().getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = getRootView().height - visibleBounds.height()
    val marginOfError = Math.round(this.convertDpToPx(50F))
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed(): Boolean {
    return !this.isKeyboardOpen()
}

fun Fragment.addKeyboardEventListener(callback: (isOpen: Boolean) -> Unit) = KeyboardEventListener(requireActivity(), lifecycle, callback)


