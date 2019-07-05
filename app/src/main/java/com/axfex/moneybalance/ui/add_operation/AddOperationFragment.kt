package com.axfex.moneybalance.ui.add_operation

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.axfex.moneybalance.R
import com.axfex.moneybalance.ui.balance.BalanceViewModel
import javax.inject.Inject

class AddOperationFragment : Fragment() {

    @Inject
    lateinit var viewModel: AddOperationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_operation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
