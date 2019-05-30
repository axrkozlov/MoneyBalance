package com.axfex.moneybalance.ui.balance

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.axfex.moneybalance.R
import com.axfex.moneybalance.utils.getViewModel


class BalanceFragment : Fragment() {

    companion object {
        fun newInstance() = BalanceFragment()
    }

    private lateinit var viewModel: BalanceViewModel

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
        viewModel = getViewModel()
        // TODO: Use the ViewModel
    }

}
