package com.axfex.moneybalance.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@Suppress
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey (
    val value:KClass<out ViewModel>
)