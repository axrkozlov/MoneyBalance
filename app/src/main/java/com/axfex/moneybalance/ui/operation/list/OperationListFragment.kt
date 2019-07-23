package com.axfex.moneybalance.ui.operation.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.axfex.moneybalance.R
import com.axfex.moneybalance.core.AppFragment
import com.axfex.moneybalance.domain.model.operation.OperationType
import com.axfex.moneybalance.utils.format
import com.axfex.moneybalance.utils.subscribe
import kotlinx.android.synthetic.main.fragment_operation_list.*
import java.math.BigDecimal
import javax.inject.Inject


class OperationListFragment : AppFragment() {

    @Inject
    lateinit var viewModel: OperationListViewModel

    @Inject
    lateinit var adapter: OperationListAdapter



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)


        return inflater.inflate(R.layout.fragment_operation_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.operationList().subscribe(this){ list ->

            var totalExpensesAmount=BigDecimal(0)
            var totalIncomesAmount=BigDecimal(0)
            adapter.submitList(list)
            list.forEach {
                if (it.type==OperationType.EXPENSE) {
                    totalExpensesAmount += it.amount
                } else if (it.type==OperationType.INCOME){
                    totalIncomesAmount += it.amount
                }
            }
            totalExpenses.text=totalExpensesAmount.format()+"$"
            totalIncomes.text=totalIncomesAmount.format()+"$"
        }



        operationsRecycler.adapter= adapter

        addExpense.setOnClickListener{
            findNavController().navigate(R.id.action_to_addOperationFragment)
        }

        addIncome.setOnClickListener{
            findNavController().navigate(R.id.action_to_addOperationFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
