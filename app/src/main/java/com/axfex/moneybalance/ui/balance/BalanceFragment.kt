package com.axfex.moneybalance.ui.balance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import kotlinx.android.synthetic.main.fragment_balance.*
import javax.inject.Inject


class BalanceFragment : AppFragment() {

    @Inject
    lateinit var viewModel: BalanceViewModel

    @Inject
    lateinit var adapter:BalanceAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_balance, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        operations.adapter=adapter
//        findNavController().navigate(R.id.action_to_categoryListFragment)
//        showDialog.setOnClickListener {
//            findNavController().navigate(R.id.action_show_categoryDialog)
//        }



        addExpense.setOnClickListener{
            findNavController().navigate(R.id.action_to_accountListFragment)
        }

        addIncome.setOnClickListener{
            findNavController().navigate(R.id.action_to_categoryListFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
