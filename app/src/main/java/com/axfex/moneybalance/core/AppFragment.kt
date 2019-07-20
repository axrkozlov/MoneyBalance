package com.axfex.moneybalance.core

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

abstract class AppFragment:Fragment() {

//    abstract val mainViewModel: V = activity?.run {
//        ViewModelProviders.of(this)[V::class.java]
//
//    } ?: throw Exception("Invalid Activity")
//
//    private val mainViewModel:MainViewModel = activity?.run {
//        ViewModelProviders.of(this)[MainViewModel::class.java]
//
//    } ?: throw Exception("Invalid Activity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)

        super.onAttach(context)
    }
}