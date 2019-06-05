package com.axfex.moneybalance.ui.balance

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import javax.inject.Inject


class BalanceFragment : AppFragment() {


//    @Inject
//    override lateinit var vmFactory: AppViewModelFactory
//
@Inject
lateinit var viewModel: BalanceViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        Log.i("BalanceFragment", "onCreateView: ")
        return inflater.inflate(R.layout.fragment_balance, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
